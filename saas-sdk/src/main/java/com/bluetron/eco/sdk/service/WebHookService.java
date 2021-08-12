package com.bluetron.eco.sdk.service;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.api.SaaSApiEnum;
import com.bluetron.eco.sdk.dto.TopicType;

import java.util.ArrayList;
import java.util.List;

public class WebHookService {
    public void subscribe(String tenantId,String  appId,String instanceName,String region,String callbackUrl,String accessToken) {
        List<String> topicList = new ArrayList<>();
        topicList.add(TopicType.TOPIC_COMPANY);
        topicList.add(TopicType.TOPIC_DEPARTMENT);
        topicList.add(TopicType.TOPIC_POSITION);
        topicList.add(TopicType.TOPIC_PERSON);
        topicList.add(TopicType.TOPIC_USER);

        JSONObject param = new JSONObject();
        param.put("appId", SecureUtil.md5(appId + ":" + tenantId));
        param.put("tenantId", instanceName.replace("ess-",""));
        param.put("webhookUrl", callbackUrl + tenantId);
        param.put("topicList", topicList);
        SaaSApi.doRequest(instanceName,region, accessToken, param, SaaSApiEnum.WEBHOOK_SUBSCRIBE, null);
    }

    public void readiness(String tenantId,String  appId,String instanceName,String region,String accessToken) {
        List<String> topicList = new ArrayList<>();
        topicList.add(TopicType.TOPIC_COMPANY);
        topicList.add(TopicType.TOPIC_DEPARTMENT);
        topicList.add(TopicType.TOPIC_POSITION);
        topicList.add(TopicType.TOPIC_PERSON);
        topicList.add(TopicType.TOPIC_USER);
        JSONObject param = new JSONObject();
        param.put("appId", SecureUtil.md5(appId + ":" + tenantId));
        param.put("tenantId", instanceName.replace("ess-",""));
        param.put("topicList", topicList);
        SaaSApi.doRequest(instanceName,region, accessToken, param, SaaSApiEnum.WEBHOOK_SUBSCRIBE_READINESS, null);
    }
}
