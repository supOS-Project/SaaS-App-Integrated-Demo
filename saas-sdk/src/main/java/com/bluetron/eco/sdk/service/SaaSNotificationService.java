package com.bluetron.eco.sdk.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.api.SaaSApiEnum;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.common.ApiResponseUtil;

import java.util.List;
import java.util.UUID;

/**
 * 消息接口
 *
 * @author caonuoqi@supos.com
 */
public class SaaSNotificationService {
    /**
     * 发送邮件消息
     *
     * @param instanceName   supos实例名称
     * @param region         实例所在区域
     * @param accessToken    token
     * @param personCodeList 接收人员code列表
     * @param content        内容
     * @return
     */
    public ApiResponse<Boolean> sendEmail(String instanceName, String region, String accessToken, List<String> personCodeList, String title, String content) {
        String bussiness = UUID.randomUUID().toString();
        String codes = JSONUtil.parseArray(personCodeList).toString();
        String request = "{\n  \"businessCode\": \"" + bussiness + "\",\n  \"businessName\": \"" + bussiness + "\",\n  \"receivers\": [\n    {\n      \"rangeType\": \"STAFF\",\n      \"codes\": " + codes + "    }\n  ],\n  \"contents\": [\n    {\n      \"protocol\": \"email\",\n      \"content\": \"{\\\"subject\\\":\\\"" + title + "\\\",\\\"text\\\":\\\"" + content + "\\\"}\"\n    }\n  ]\n}";
        JSONObject params = JSONObject.parseObject(request);
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, params, SaaSApiEnum.NOTIFICATION_SEND_MESSAGE, new Object[0]);
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }

    /**
     * 发送站内信
     *
     * @param instanceName   supos实例名称
     * @param region         实例所在区域
     * @param accessToken    token
     * @param personCodeList 接收人员code列表
     * @param content        内容
     * @return
     */
    public ApiResponse<Boolean> sendStationLetter(String instanceName, String region, String accessToken, List<String> personCodeList, String content) {
        String bussiness = UUID.randomUUID().toString();
        String codes = JSONUtil.parseArray(personCodeList).toString();
        String request = "{\n  \"businessCode\": \"" + bussiness + "\",\n  \"businessName\": \"" + bussiness + "\",\n  \"receivers\": [\n    {\n      \"rangeType\": \"STAFF\",\n      \"codes\": " + codes + "    }\n  ],\n  \"contents\": [\n    {\n      \"protocol\": \"stationLetter\",\n      \"content\": \"{\\\"text\\\":\\\"" + content + "\\\"}\"\n    }\n  ]\n}";
        JSONObject params = JSONObject.parseObject(request);
        HttpResponse response = SaaSApi.doRequest(instanceName, region, accessToken, params, SaaSApiEnum.NOTIFICATION_SEND_MESSAGE, new Object[0]);
        return response.isOk() ? ApiResponseUtil.getSuccessResponse(true) : ApiResponseUtil.responseFailed(response, (Exception) null);
    }
}
