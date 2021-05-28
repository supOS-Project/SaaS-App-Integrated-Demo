package com.dev.happy.tenant.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.org.CompanyRes;
import com.bluetron.eco.sdk.dto.org.PersonRes;
import com.bluetron.eco.sdk.dto.user.UserRes;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisStandaloneUtils redisStandaloneUtils;
    @GetMapping("/saas/userInfo")
    public ApiResponse  getSaaSUserInfo(String username,String tenantId){
        String accessToken=redisStandaloneUtils.get("access-token");
        Tenant tenant= JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId),Tenant.class);
        ApiResponse<UserRes> userInfo= SaaSApi.userService.getInfo(tenant.getInstanceName(),tenant.getRegion(),accessToken,username);
        return userInfo;
    }
    @GetMapping("/saas/personInfo")
    public ApiResponse  getSaaSPersonInfo(String code,String tenantId){
        Tenant tenant= JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId),Tenant.class);
        String accessToken=redisStandaloneUtils.get("access-token");
        ApiResponse<PersonRes> userInfo= SaaSApi.personService.getInfo("ess-cgkg","nb02",accessToken,code);
        return userInfo;
    }
    @GetMapping("/saas/companyInfo")
    public ApiResponse  getSaaSCompanyInfo(String code,String tenantId){
        String accessToken=redisStandaloneUtils.get("access-token");
        Tenant tenant= JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId),Tenant.class);
        ApiResponse<CompanyRes> companyDetail= SaaSApi.orgService.getCompanyDetail("ess-cgkg","nb02",accessToken,code);
        return companyDetail;
    }
    @GetMapping("/saas/sendStationLetter")
    public ApiResponse  sendStationLetter(String userCode,String tenantId){
        Tenant tenant= JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId),Tenant.class);
        String accessToken=redisStandaloneUtils.get("access-token");
        List<String> person=new ArrayList<>();
        person.add(userCode);
        String context="测试内容";
        ApiResponse<Boolean> x= SaaSApi.notificationService.sendStationLetter("ess-cgkg","nb02",accessToken,person,context);
        return x;
    }
}
