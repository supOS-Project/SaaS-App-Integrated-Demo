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

/**
 * 人员相关接口
 *
 * @author caonuoqi@supos.com
 */
public class SaaSPersonService {
    /**
     * 根据人员code获取人员详情
     *
     * @param instanceName supos实例名称
     * @param region       实例所在区域
     * @param accessToken  token
     * @param code         人员编码
     * @return
     */
    public ApiResponse<PersonRes> getInfo(String instanceName, String region, String accessToken, String code) {
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, SaaSApiEnum.PERSON_DETAIL, new Object[]{code});
        return ApiResponseUtil.responseIfSuccess(response, PersonRes.class);
    }

    /**
     * 获取人员列表
     *
     * @param instanceName supos实例名称
     * @param region       实例所在区域
     * @param accessToken  token
     * @param param        查询参数
     * @return
     */
    public ApiResponse<ResultRes<PersonRes>> getList(String instanceName, String region, String accessToken, BaseListRequest param) {
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, param, SaaSApiEnum.PERSON_LIST, new Object[0]);
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PersonRes>>() {
        });
    }

    /**
     * 查询指定编码的公司的人员列表
     *
     * @param instanceName supos实例名称
     * @param region       实例所在区域
     * @param accessToken  token
     * @param param        查询参数
     * @return
     */
    public ApiResponse<ResultRes<PersonRes>> getListByCompanyCode(String instanceName, String region, String accessToken, CompanyPersonReq param) {
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, param, SaaSApiEnum.PERSON_LIST_BY_COMPANY, new Object[]{param.getCompanyCode()});
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PersonRes>>() {
        });
    }

    /**
     * 查询指定编码的部门的人员列表
     *
     * @param instanceName supos实例名称
     * @param region       实例所在区域
     * @param accessToken  token
     * @param param        查询参数
     * @return
     */
    public ApiResponse<ResultRes<PersonRes>> getListByDepartmentCode(String instanceName, String region, String accessToken, DepartmentPersonReq param) {
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, param, SaaSApiEnum.PERSON_LIST_BY_DEPARTMENTS, new Object[]{param.getDepartmentCode()});
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PersonRes>>() {
        });
    }

    /**
     * 查询指定编码的岗位的人员列表
     *
     * @param instanceName supos实例名称
     * @param region       实例所在区域
     * @param accessToken  token
     * @param param        查询参数
     * @return
     */
    public ApiResponse<ResultRes<PersonRes>> getListByPositionCode(String instanceName, String region, String accessToken, PositionPersonReq param) {
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, param, SaaSApiEnum.PERSON_LIST_BY_POSITION, new Object[]{param.getPositionCode()});
        return ApiResponseUtil.responseIfSuccess(response, new TypeReference<ResultRes<PersonRes>>() {
        });
    }
}
