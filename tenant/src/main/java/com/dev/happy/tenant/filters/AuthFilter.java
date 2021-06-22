package com.dev.happy.tenant.filters;

import com.alibaba.fastjson.JSONObject;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 应用的权限过滤
 *
 * @author caonuoqi@supos.com
 */
@Component
public class AuthFilter implements Filter {
    @Resource
    private RedisStandaloneUtils redisStandaloneUtils;
    private static String[] excludeUris = new String[]{
            "/index",
            "^/open-api/app/",
            "^/home",
            "^/auth/toHome"
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
        //获取应用的token
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            if (StringUtils.isNotBlank(redisStandaloneUtils.get(token))) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                JSONObject res = new JSONObject();
                res.put("code", 401);
                res.put("msg", "token失效或为空，请重新进入");
                res.put("success", "false");
                out.append(res.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
