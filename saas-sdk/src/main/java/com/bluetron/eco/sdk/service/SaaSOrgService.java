package com.bluetron.eco.sdk.service;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.TypeReference;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.api.SaaSApiEnum;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.common.ApiResponseUtil;
import com.bluetron.eco.sdk.dto.common.BaseListRequest;
import com.bluetron.eco.sdk.dto.common.ResultRes;
import com.bluetron.eco.sdk.dto.org.*;

public class SaaSOrgService {
    /**
     * 公司列表
     * @param instanceName
     * @param region
     * @param accessToken
     * @param param
     * @return
     */
    public ApiResponse<ResultRes<CompanyRes>> getCompanyList(String instanceName, String region, String accessToken, BaseListRequest param) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,param, SaaSApiEnum.COMPANY_LIST, new Object[0]);
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<CompanyRes>>() {
        });
    }

    /**
     * 公司详情
     * @param instanceName
     * @param region
     * @param accessToken
     * @param code
     * @return
     */
    public ApiResponse<CompanyRes> getCompanyDetail(String instanceName,String region,String accessToken,String code) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,SaaSApiEnum.COMPANY_DETAIL, new Object[]{code});
        return ApiResponseUtil.responseIfSuccess(response, CompanyRes.class);
    }

    /**
     * 部门列表
     * @param instanceName
     * @param region
     * @param accessToken
     * @param param
     * @return
     */
    public ApiResponse<ResultRes<DepartmentRes>> getDepartmentList(String instanceName,String region,String accessToken,BaseListRequest param) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,param, SaaSApiEnum.DEPARTMENTS_LIST, new Object[0]);
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<DepartmentRes>>() {
        });
    }

    /**
     * 部门详情
     * @param instanceName
     * @param region
     * @param accessToken
     * @param code
     * @return
     */
    public ApiResponse<DepartmentRes> getDepartmentDetail(String instanceName,String region,String accessToken,String code) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,SaaSApiEnum.DEPARTMENTS_DETAIL, new Object[]{code});
        return ApiResponseUtil.responseIfSuccess(response, DepartmentRes.class);
    }

    /**
     * 岗位列表
     * @param instanceName
     * @param region
     * @param accessToken
     * @param param
     * @return
     */
    public ApiResponse<ResultRes<PositionRes>> getPositionList(String instanceName,String region,String accessToken,BaseListRequest param) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,param, SaaSApiEnum.POSITION_LIST, new Object[0]);
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PositionRes>>() {
        });
    }

    /**
     * 岗位详情
     * @param instanceName
     * @param region
     * @param accessToken
     * @param code
     * @return
     */
    public ApiResponse<PositionRes> getPositionDetail(String instanceName,String region,String accessToken,String code) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,SaaSApiEnum.POSITION_DETAIL, new Object[]{code});
        return ApiResponseUtil.responseIfSuccess(response, PositionRes.class);
    }

    /**
     * 查询指定编码的公司的部门列表
     * @param instanceName
     * @param region
     * @param accessToken
     * @param param
     * @return
     */
    @Deprecated
    public ApiResponse<ResultRes<PositionRes>> getPositionListByCompanyCode(String instanceName, String region, String accessToken, CompanyPositionReq param){
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,param, SaaSApiEnum.POSITION_LIST_BY_COMPANY, new Object[]{param.getCompanyCode()});
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PositionRes>>() {
        });
    }
    /**
     * 查询指定编码的公司的岗位列表
     * @param instanceName
     * @param region
     * @param accessToken
     * @param param
     * @return
     */
    @Deprecated
    public ApiResponse<ResultRes<DepartmentRes>> getDepartmentListByCompanyCode(String instanceName,String region,String accessToken,CompanyDepartmentReq param) {
        HttpResponse response = SaaSApi.doRequest(instanceName,region,accessToken,param, SaaSApiEnum.DEPARTMENTS_LIST_BY_COMPANY, new Object[]{param.getCompanyCode()});
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<DepartmentRes>>() {
        });
    }
}
