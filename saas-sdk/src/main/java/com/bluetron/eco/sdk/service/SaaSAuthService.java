package com.bluetron.eco.sdk.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.dto.auth.AuthAccessToken;
import com.bluetron.eco.sdk.dto.auth.SaaSAuthorizeReq;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.common.ApiResponseUtil;
import com.bluetron.eco.sdk.config.SuposConfig;

import java.util.HashMap;
import java.util.Map;

public class SaaSAuthService {
    private static final String SAAS_OAUTH_BASE = SuposConfig.getBossBaseUrl()+"/services/oauth2";
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
        SaaSAuthorizeReq param = new SaaSAuthorizeReq();
        param.setAppid(appid);
        param.setRedirectUri(redirectUri);
        param.setState(state);
        String scope = "openid," + region + "," + instanceName;
        param.setScope(scope);
        return path.concat("?").concat(param.toAuthorizeStr());

    }

    /**
     * 获取accessToken
     * @param authorizeCode
     * @param logoutUri
     * @return
     */
    public ApiResponse<AuthAccessToken> accessToken(String authorizeCode, String logoutUri) {
        String path = SAAS_OAUTH_BASE + "/oauth/accessToken";

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("grantType", "authorization_code");
        queryParams.put("appid", SuposConfig.getBossAppId());
        queryParams.put("secret", SuposConfig.getBossSecretKey());
        queryParams.put("code", authorizeCode);
        String result = HttpUtil.get(path, queryParams);
        JSONObject json = JSONObject.parseObject(result);
        AuthAccessToken accessToken = JSONObject.parseObject(json.get("data").toString(), AuthAccessToken.class);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(json.getIntValue("code"));
        apiResponse.setMessage(json.getString("message"));
        apiResponse.setData(accessToken);
        return apiResponse;
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    public ApiResponse<AuthAccessToken> refreshToken(String refreshToken) {
        String path = SAAS_OAUTH_BASE + "/oauth/refreshToken";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("refreshToken", refreshToken);
        String result = HttpUtil.get(path, queryParams);
        JSONObject json = JSONObject.parseObject(result);
        if (!"true".equals(json.get("success").toString())) {
            throw new RuntimeException("刷新token返回失败！");
        }
        AuthAccessToken accessToken = JSONObject.parseObject(json.get("data").toString(), AuthAccessToken.class);
        return ApiResponseUtil.getSuccessResponse(accessToken);
    }
}
