package com.dev.happy.tenant.controller;

import com.dev.happy.tenant.service.TenantService;
import com.dev.happy.tenant.service.UserService;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
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

    @RequestMapping("/redirect")
    public void redirect(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Location", request.getParameter("url"));
        response.setStatus(HttpStatus.FOUND.value());
    }


}
