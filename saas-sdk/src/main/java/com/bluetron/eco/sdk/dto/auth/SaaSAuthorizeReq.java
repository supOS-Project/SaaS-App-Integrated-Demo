package com.bluetron.eco.sdk.dto.auth;

import java.io.Serializable;

public class SaaSAuthorizeReq implements Serializable {
    private String responseType="code";
    private String appid;
    private String redirectUri;
    private String state;
    private String scope;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String toAuthorizeStr(){
       return  "responseType=" + responseType + "&" +
                "appid=" + appid + "&" +
                "redirectUri=" + redirectUri + "&" +
                "state=" + state + "&" +
                "scope=" + scope ;
    }
}
