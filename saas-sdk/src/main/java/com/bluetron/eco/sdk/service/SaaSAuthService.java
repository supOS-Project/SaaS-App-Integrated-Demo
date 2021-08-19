package com.bluetron.eco.sdk.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.config.SuposConfig;
import com.bluetron.eco.sdk.dto.common.ResponseResult;

import java.util.HashMap;
import java.util.Map;

public class SaaSAuthService {
    private static final String SAAS_OAUTH_BASE = SuposConfig.getBossBaseUrl() + "/services/oauth2";

    /***
     * oauth2 授权跳转地址
     * @param redirectUri
     * @param state
     * @param appid
     * @param region
     * @param instanceName
     * @return
     */
    public String authorize(String redirectUri, String state, String appid, String region, String instanceName) {
        String path = SAAS_OAUTH_BASE + "/oauth/authorize";
        path= path.concat("?responseType=code")
                  .concat("&appid=").concat(appid)
                  .concat("&redirectUri=").concat(redirectUri)
                  .concat("&state=").concat(state)
                  .concat("&scope=").concat("openid," + region + "," + instanceName);
        return path;
    }

    /**
     * 获取accessToken
     *
     * @param authorizeCode
     * @param logoutUri
     * @return
     */
    public ResponseResult<JSONObject> accessToken(String authorizeCode, String logoutUri) {
        String path = SAAS_OAUTH_BASE + "/oauth/accessToken";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("grantType", "authorization_code");
        queryParams.put("appid", SuposConfig.getBossAppId());
        queryParams.put("secret", SuposConfig.getBossSecretKey());
        queryParams.put("code", authorizeCode);
        String result = HttpUtil.get(path, queryParams);
        JSONObject json = JSONUtil.parseObj(result);
        JSONObject accessToken=json.getJSONObject("data");
        return ResponseResult.SUCCESS(accessToken);
    }

    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    public ResponseResult<JSONObject> refreshToken(String refreshToken) {
        String path = SAAS_OAUTH_BASE + "/oauth/refreshToken";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("refreshToken", refreshToken);
        String result = HttpUtil.get(path, queryParams);
        JSONObject json = JSONUtil.parseObj(result);
        if (!"true".equals(json.get("success").toString())) {
            throw new RuntimeException("刷新token返回失败！");
        }
        JSONObject accessToken=json.getJSONObject("data");
        return ResponseResult.SUCCESS(accessToken);
    }
}
