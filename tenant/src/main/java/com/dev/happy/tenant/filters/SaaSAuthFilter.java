package com.dev.happy.tenant.filters;

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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cnq
 */
@Slf4j
@Component
public class SaaSAuthFilter implements Filter {
    @Resource
    private RedisStandaloneUtils redisStandaloneUtils;
    ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(100);
    private final static String ACCESS_TOKEN="access-token";
    private final static String REFRESH_TOKEN="refresh-token";
    @Resource
    private TenantService tenantService;
    @Resource
    private UserService userService;
    private static final String LOGOUT_URI = "/logout";
    private static final String AUTH_KEY = "token";
    private static String[] excludeUris = new String[]{
            "/index",
            "^/open-api/app/",
            "^/test/"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURL().toString();
        String queryParams = request.getQueryString();
        if (excludeUris != null && excludeUris.length > 0) {
            for (int i = 0; i < excludeUris.length; i++) {
                Pattern p = Pattern.compile(excludeUris[i]);
                Matcher matcher = p.matcher(request.getRequestURI());
                boolean matched=matcher.find();
                if(matched){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        // SaaSApp 调用supos的oauth2及open-api强关联租户id，需要租户id定位租户所在实例的名称及区域，同时也可以通过租户id确认请求是否合法
        String tenantId = request.getParameter("tenantId");
        String tenantCache=redisStandaloneUtils.get("TENANT-" + tenantId);
        Tenant tenant=null;
        if(StringUtils.isNotBlank(tenantCache)){
            tenant= JSONObject.parseObject(tenantCache,Tenant.class);
        }else{
            tenant = tenantService.getTenant(null, null, tenantId);
            if(tenant==null){
                returnJson(response,100015,String.format("tenantId为%s的租户不存在",tenantId));
                return;
            }
            redisStandaloneUtils.set("TENANT-" + tenantId,JSONObject.toJSONString(tenant));
        }

        String accessTokenCache=  redisStandaloneUtils.hget(ACCESS_TOKEN,tenantId);
        if (StringUtils.isBlank(accessTokenCache)) {
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
                    redisStandaloneUtils.hset(REFRESH_TOKEN,tenantId,authAccessToken.getRefreshToken());
                    redisStandaloneUtils.hset(ACCESS_TOKEN,tenantId,authAccessToken.getAccessToken());
                    redisStandaloneUtils.setex(tenantId,tenantId,authAccessToken.getExpiresIn().intValue());
                String username=authAccessToken.getUsername();
                //TODO SaaS服务自身的权限业务 eg:
                    User loginUser=userService.getByName(username);
                    String tokenKey=tenantId+":USER-"+username+LocalDateTime.now().minusMinutes(30).toEpochSecond(ZoneOffset.UTC);
                    redisStandaloneUtils.set(tokenKey,JSONObject.toJSONString(loginUser));
                    ((HttpServletResponse) servletResponse).setHeader(Header.SET_COOKIE.getValue(),"token="+tokenKey);
                }
                //启动定时线程，定时刷新token
                scheduledExecutorService.scheduleAtFixedRate(() -> {
                  Map<String,String> refreshTokens= redisStandaloneUtils.hgetAll(REFRESH_TOKEN);
                  if(refreshTokens!=null && !refreshTokens.isEmpty()){
                      refreshTokens.entrySet().forEach(x->{
                          if(redisStandaloneUtils.exists(x.getKey())){
                              log.info("开始刷新token，tenantId：{}",x.getKey());
                              ApiResponse<AuthAccessToken> refreshToken = SaaSApi.authService.refreshToken(x.getValue());
                              AuthAccessToken authRefreshToken = refreshToken.getData();
                              redisStandaloneUtils.hset(REFRESH_TOKEN,x.getKey(),authRefreshToken.getRefreshToken());
                              redisStandaloneUtils.hset(ACCESS_TOKEN,x.getKey(),authRefreshToken.getAccessToken());
                              redisStandaloneUtils.setex(x.getKey(),x.getKey(),authRefreshToken.getExpiresIn().intValue());
                          }else{
                              log.info("删除过期租户的token，tenantId：{}",x.getKey());
                              redisStandaloneUtils.hdel(REFRESH_TOKEN,x.getKey());
                              redisStandaloneUtils.hdel(ACCESS_TOKEN,x.getKey());
                          }
                      });
                  }
                },authAccessToken.getExpiresIn()/15,authAccessToken.getExpiresIn()/8,TimeUnit.SECONDS);

                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            String token=getCookieByName(request,AUTH_KEY);
           if(StringUtils.isNotBlank(token) && StringUtils.isNotBlank(redisStandaloneUtils.get(token))){
               filterChain.doFilter(servletRequest, servletResponse);
           }else{
              //SaaS本身的鉴权过期，或者接口未有鉴权信息时跳转到supos的oauth2鉴权地址
               String location = SaaSApi.authService.authorize(requestUrl, "1", tenant.getAppId(), tenant.getRegion(), tenant.getInstanceName());
               log.info("重定向地址：{}", location);
               response.addHeader("Location", location);
               response.setStatus(HttpStatus.FOUND.value());
               redisStandaloneUtils.hdel(ACCESS_TOKEN,tenantId);
               redisStandaloneUtils.hdel(REFRESH_TOKEN,tenantId);
              return;
           }
        }
    }

    /**
     * 获取指定cookie值
     * @param request
     * @param name
     * @return
     */
    private String getCookieByName(HttpServletRequest request,String name){
        Cookie[] cookies=request.getCookies();

        if(cookies!=null && cookies.length>0){
          Optional<Cookie> optional= Arrays.stream(cookies).filter(x->x.getName().equals(name)).findAny();
            if(optional.isPresent()){
                return optional.get().getValue();
            }
        }
        return null;
    }
    private void returnJson(HttpServletResponse response,int code,String msg)throws  IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        res.put("code",code);
        res.put("msg",msg);
        res.put("success", "false");
        out.append(res.toString());
    }
}
