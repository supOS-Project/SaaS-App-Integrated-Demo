package com.dev.happy.tenant.filters;

import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.dto.auth.AuthAccessToken;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.user.UserRes;
import com.dev.happy.tenant.constant.AuthConstant;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.service.SyncService;
import com.dev.happy.tenant.service.TenantService;
import com.dev.happy.tenant.service.UserService;
import com.dev.happy.tenant.utils.CookieUtil;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 应用的权限过滤
 *
 * @author caonuoqi@supos.com
 */
@Slf4j
@Component
public class AuthFilter implements Filter {
    @Resource
    private RedisStandaloneUtils redisStandaloneUtils;
    @Resource
    private TenantService tenantService;
    @Resource
    private UserService userService;
    @Resource
    private SyncService syncService;
    private static final String LOGOUT_URI = "/logout";
    private static String[] excludeUris = new String[]{
            "^/open-api/app/",
            "^/message/callback/"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (excludeUris != null && excludeUris.length > 0) {
            for (int i = 0; i < excludeUris.length; i++) {
                Pattern p = Pattern.compile(excludeUris[i]);
                Matcher matcher = p.matcher(request.getRequestURI());
                boolean matched = matcher.find();
                if (matched) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        String tenantUserToken= CookieUtil.getCookie("userToken",request);
        if(StringUtils.isNotBlank(tenantUserToken)){
            if(StringUtils.isBlank((String)redisStandaloneUtils.get(tenantUserToken))){
                returnJson(response,401,"token失效，请重新进入！");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
           String code=request.getParameter("code");
            String tenantId = request.getParameter("tenantId");
            String tenantCache = (String)redisStandaloneUtils.get("TENANT-" + tenantId);
            Tenant tenant = null;
            if (StringUtils.isNotBlank(tenantCache)) {
                tenant = JSONObject.parseObject(tenantCache, Tenant.class);
            } else {
                tenant = tenantService.getTenant(null, null, tenantId);
                if (tenant == null) {
                    returnJson(response, 100015, String.format("tenantId为%s的租户不存在", tenantId));
                    return;
                }
                redisStandaloneUtils.set("TENANT-" + tenantId, JSONObject.toJSONString(tenant));
            }
            if (StringUtils.isBlank(code)) {
                String requestUrl = request.getRequestURL().toString();
                String queryParams = request.getQueryString();
                if (StringUtils.isNotBlank(queryParams)) {
                    requestUrl = requestUrl + "?" + queryParams;
                }
                String location = SaaSApi.authService.authorize(requestUrl, "1", tenant.getAppId(), tenant.getRegion(), tenant.getInstanceName());
                log.info("重定向地址：{}", location);
                response.addHeader("Location", location);
                response.setStatus(HttpStatus.FOUND.value());
            } else {
                ApiResponse<AuthAccessToken> accessToken = SaaSApi.authService.accessToken(code, LOGOUT_URI);
                AuthAccessToken authAccessToken = accessToken.getData();
                if (accessToken.getCode().equals(1)) {
                    String key = tenantId + ":" + authAccessToken.getUsername();
                    redisStandaloneUtils.set(AuthConstant.REFRESH_TOKEN+":"+key,authAccessToken.getRefreshToken(), authAccessToken.getExpiresIn().intValue());
                    redisStandaloneUtils.set(AuthConstant.ACCESS_TOKEN+":"+key,authAccessToken.getAccessToken(), authAccessToken.getExpiresIn().intValue());
                    String username = authAccessToken.getUsername();
                    //TODO  SaaS服务自身的权限业务,此处相当于应用原来的登录成功后操作 eg:
                    User loginUser = userService.getByTenantIdAndName(tenantId, username);
                    //如果应用的用户表中没有用户 则使用open-api获取登录用户信息并插入用户表中
                    if (loginUser == null) {
                        ApiResponse<UserRes> res = SaaSApi.userService.getInfo(tenant.getInstanceName(), tenant.getRegion(), authAccessToken.getAccessToken(), username);
                        loginUser = userService.saveAndGet(tenantId, res.getData());
                    }
                    //首次登录时同步基础信息
                    syncService.syncAll(tenant,authAccessToken.getAccessToken());
                    //需要将tenantId及username放入token中，以便根据tenantId+username获取supOS的对应用户的accessToken
                    String tokenKey = key + "-" + UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
                    redisStandaloneUtils.set(tokenKey, JSONObject.toJSONString(loginUser), 30*60);
                    response.addHeader("Set-Cookie", "userToken=" + tokenKey+";path=/;HttpOnly=true;SameSite=None");
                    response.addHeader("Set-Cookie", "tenantId=" + tenantId+";path=/;HttpOnly=true;SameSite=None");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
       return;
    }
    private void returnJson(HttpServletResponse response, int code, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            JSONObject res = new JSONObject();
            res.put("code", code);
            res.put("msg", msg);
            res.put("success", "false");
            out.append(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
