package com.dev.happy.tenant.controller;

import cn.hutool.http.Header;
import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.dto.auth.AuthAccessToken;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.service.TenantService;
import com.dev.happy.tenant.service.UserService;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * SaaS应用入口Controller
 *
 * @author caonuoqi@supos.com
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private RedisStandaloneUtils redisStandaloneUtils;
    @Resource
    private TenantService tenantService;
    @Resource
    private UserService userService;
    private final static String ACCESS_TOKEN="access-token";
    private final static String REFRESH_TOKEN="refresh-token";
    private static final String LOGOUT_URI = "/logout";
    @Value("${app.home-url}")
    private String homeUrl;
    /**
     * 应用入口<br>
     * 先进行supOS的oauth2鉴权，若有权限跳转到应用主页，若无跳转到supOS授权<br>
     * 每次进来进行重新授权，防止不同用户使用相同的accessToken 导致获取当前用户信息错误
     * @param request
     * @param response
     */
    @RequestMapping("/toHome")
    public void auto(HttpServletRequest request, HttpServletResponse response) {
        String tenantId = request.getParameter("tenantId");
        String requestUrl = request.getRequestURL().toString();
        String queryParams = request.getQueryString();
        String tenantCache = redisStandaloneUtils.get("TENANT-" + tenantId);
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
            String code = request.getParameter("code");
            if (StringUtils.isBlank(code)) {
                if (StringUtils.isNotBlank(queryParams)) {
                    requestUrl = requestUrl + "?" + queryParams;
                }
                String location = SaaSApi.authService.authorize(requestUrl, "1", tenant.getAppId(), tenant.getRegion(), tenant.getInstanceName());
                log.info("重定向地址：{}", location);
                response.addHeader("Location", location);
                response.setStatus(HttpStatus.FOUND.value());
            } else {
                ApiResponse<AuthAccessToken> accessToken = SaaSApi.authService.accessToken(code, LOGOUT_URI);
                AuthAccessToken authAccessToken=accessToken.getData();
                if (accessToken.getCode().equals(1)) {
                    String key=tenantId+":"+authAccessToken.getUsername();
                    redisStandaloneUtils.hset(REFRESH_TOKEN,key,authAccessToken.getRefreshToken());
                    redisStandaloneUtils.hset(ACCESS_TOKEN,key,authAccessToken.getAccessToken());
                    redisStandaloneUtils.setex(key,UUID.randomUUID().toString(),authAccessToken.getExpiresIn().intValue());
                    String username=authAccessToken.getUsername();
                    //TODO SaaS服务自身的权限业务,此处相当于应用原来的登录操作 eg:
                    User loginUser=userService.getByName(username);
                    //需要将tenantId及username放入token中，以便根据tenantId+username获取supOS的对应用户的accessToken
                    String tokenKey=key+"-"+ UUID.randomUUID().toString().toLowerCase().replaceAll("-","");
                    redisStandaloneUtils.setex(tokenKey,JSONObject.toJSONString(loginUser),30,TimeUnit.MINUTES);
                    //最新版谷歌浏览器存在cookie跨域丢失的，需增加SameSite=None; Secure=true属性
                    response.setHeader(Header.SET_COOKIE.getValue(),"token="+tokenKey+";Path=/; SameSite=None; Secure=true");
                    response.addHeader("Location", homeUrl+"?tenantId="+tenantId);
                    response.setStatus(HttpStatus.FOUND.value());
                }
            }
    }

    private void returnJson(HttpServletResponse response, int code, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out ;
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
