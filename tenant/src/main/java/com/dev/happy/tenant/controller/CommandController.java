package com.dev.happy.tenant.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.common.BaseListRequest;
import com.bluetron.eco.sdk.dto.org.*;
import com.bluetron.eco.sdk.dto.user.GetAllUsersReq;
import com.bluetron.eco.sdk.dto.user.UpdateUserReq;
import com.bluetron.eco.sdk.dto.user.UserBindRole;
import com.bluetron.eco.sdk.dto.user.UserReq;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author caonuoqi@supos.com
 */
@RestController
@RequestMapping("command")
public class CommandController {
    @Autowired
    private RedisStandaloneUtils redisStandaloneUtils;

    @PostMapping("/exec")
    public ApiResponse exec(String method, @RequestBody JSONObject params, HttpServletRequest request) {
        String tenantId = params.getString("tenantId");
        if (StringUtils.isBlank(tenantId)) {
            return new ApiResponse("tenantId不能为空", null, 200, 10000);
        }
       String token= request.getHeader("token");
        String accessTokenKey=token.split("-")[0];
        String accessToken = redisStandaloneUtils.hget("access-token",accessTokenKey);
        Tenant tenant = JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId), Tenant.class);
        switch (method) {
            case "users/detail":
                //ok
                return SaaSApi.userService.getInfo(tenant.getInstanceName(), tenant.getRegion(), accessToken, params.getString("username"));
            case "users/add":
                 // ok
                UserReq userReq = params.toJavaObject(UserReq.class);
                return SaaSApi.userService.add(tenant.getInstanceName(), tenant.getRegion(), accessToken, userReq);
            case "users/update":
                // ok
                UpdateUserReq updateUserReq = params.toJavaObject(UpdateUserReq.class);
                return SaaSApi.userService.update(tenant.getInstanceName(), tenant.getRegion(), accessToken, updateUserReq);
            case "users/batchDelete":
                List<String> nameList = params.getJSONArray("nameList").toJavaList(String.class);
                return SaaSApi.userService.batchDelete(tenant.getInstanceName(), tenant.getRegion(), accessToken, nameList);
            case "users/list":
                // ok
                GetAllUsersReq usersReq = params.toJavaObject(GetAllUsersReq.class);
                return SaaSApi.userService.getList(tenant.getInstanceName(), tenant.getRegion(), accessToken, usersReq);
            case "users/bindRole":
                // ok
                UserBindRole userBindRole = params.toJavaObject(UserBindRole.class);
                return SaaSApi.userService.bindRole(tenant.getInstanceName(), tenant.getRegion(), accessToken, userBindRole);
            case "users/unbindRole":
                //ok
                UserBindRole unbindRole = params.toJavaObject(UserBindRole.class);
                return SaaSApi.userService.unbindRole(tenant.getInstanceName(), tenant.getRegion(), accessToken, unbindRole);
            case "company/list":
                // ok
                BaseListRequest orgReq = params.toJavaObject(BaseListRequest.class);
                return SaaSApi.orgService.getCompanyList(tenant.getInstanceName(), tenant.getRegion(), accessToken, orgReq);
            case "company/detail":
                //ok
                String companyCode = params.getString("companyCode");
                return SaaSApi.orgService.getCompanyDetail(tenant.getInstanceName(), tenant.getRegion(), accessToken, companyCode);
            case "department/list":
                //ok
                BaseListRequest departmentReq = params.toJavaObject(BaseListRequest.class);
                return SaaSApi.orgService.getDepartmentList(tenant.getInstanceName(), tenant.getRegion(), accessToken, departmentReq);
            case "department/listByCompany":
                CompanyDepartmentReq companyDepartment=params.toJavaObject(CompanyDepartmentReq.class);
                return SaaSApi.orgService.getDepartmentListByCompanyCode(tenant.getInstanceName(), tenant.getRegion(), accessToken, companyDepartment);
            case "department/detail":
                //ok
                String departmentCode = params.getString("departmentCode");
                return SaaSApi.orgService.getDepartmentDetail(tenant.getInstanceName(), tenant.getRegion(), accessToken, departmentCode);
            case "position/list":
                // ok
                BaseListRequest positionReq = params.toJavaObject(BaseListRequest.class);
                return SaaSApi.orgService.getPositionList(tenant.getInstanceName(), tenant.getRegion(), accessToken, positionReq);
            case "position/listByCompanyCode":
                CompanyPositionReq companyPosition=params.toJavaObject(CompanyPositionReq.class);
                return SaaSApi.orgService.getPositionListByCompanyCode(tenant.getInstanceName(),tenant.getRegion(),accessToken,companyPosition);
            case "position/detail":
                //ok
                String positionCode = params.getString("positionCode");
                return SaaSApi.orgService.getPositionDetail(tenant.getInstanceName(), tenant.getRegion(), accessToken, positionCode);
            case "person/detail":
                // ok
                String personCode=params.getString("personCode");
                return SaaSApi.personService.getInfo(tenant.getInstanceName(),tenant.getRegion(),accessToken,personCode);
            case "person/list":
                // ok
                BaseListRequest personReq = params.toJavaObject(BaseListRequest.class);
                return SaaSApi.personService.getList(tenant.getInstanceName(),tenant.getRegion(),accessToken,personReq);
            case "person/listByCompanyCode":
                 // ok
                CompanyPersonReq companyCodeSearch = params.toJavaObject(CompanyPersonReq.class);
                return SaaSApi.personService.getListByCompanyCode(tenant.getInstanceName(),tenant.getRegion(),accessToken,companyCodeSearch);
            case "person/listByDepartmentCode":
                // ok
                DepartmentPersonReq departmentCodeSearch = params.toJavaObject(DepartmentPersonReq.class);
                return SaaSApi.personService.getListByDepartmentCode(tenant.getInstanceName(),tenant.getRegion(),accessToken,departmentCodeSearch);
            case "person/listByPositionCode":
                //ok
                PositionPersonReq positionCodeSearch=params.toJavaObject(PositionPersonReq.class);
                return SaaSApi.personService.getListByPositionCode(tenant.getInstanceName(),tenant.getRegion(),accessToken,positionCodeSearch);
            default:
                break;
        }
        return null;
    }
}
