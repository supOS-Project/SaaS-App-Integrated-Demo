package com.bluetron.eco.sdk.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.api.SaaSApiEnum;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.common.ApiResponseUtil;
import com.bluetron.eco.sdk.dto.user.*;

import java.util.List;

public class SaaSUserService {
    public ApiResponse<Boolean> add(String instanceName,String region,String accessToken,UserReq userReq) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,userReq, SaaSApiEnum.USER_ADD, new Object[0]);
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }

    public ApiResponse<UserRes> getInfo(String instanceName,String region,String accessToken,String username) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,SaaSApiEnum.USER_DETAIL, new Object[]{username});
        if (response.isOk()) {
            UserRes userRes = JSONUtil.toBean(JSONUtil.parseObj(response.body()), UserRes.class);
            return ApiResponseUtil.getSuccessResponse(userRes);
        } else {
            return ApiResponseUtil.responseFailed(response, null);
        }
    }

    public ApiResponse<Boolean> update(String instanceName,String region,String accessToken,UpdateUserReq updateUserReq) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,updateUserReq, SaaSApiEnum.USER_UPDATE, new Object[]{updateUserReq.getUsername()});
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }

    public ApiResponse<Boolean> batchDelete(String instanceName,String region,String accessToken,List<String> nameList) {
       BatchUserReq batchUserReq=new BatchUserReq(nameList);
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,batchUserReq, SaaSApiEnum.USER_BATCH_DELETE, new Object[0]);
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }

    public ApiResponse<UsersRes> getList(String instanceName,String region,String accessToken,GetAllUsersReq usersReq) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,usersReq, SaaSApiEnum.USER_LISTS, new Object[0]);
        return ApiResponseUtil.responseIfSuccess(response, UsersRes.class);
    }

    public ApiResponse<Boolean> bindRole(String instanceName,String region,String accessToken,UserBindRole userBindRole) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,userBindRole, SaaSApiEnum.USER_BIND_ROLE, new Object[]{userBindRole.getUsername()});
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }

    public ApiResponse<Boolean> unbindRole(String instanceName,String region,String accessToken,UserBindRole userBindRole) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,userBindRole, SaaSApiEnum.USER_UNBIND_ROLE, new Object[]{userBindRole.getUsername()});
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }
}
