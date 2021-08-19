package com.bluetron.eco.sdk.api;

import cn.hutool.http.Method;

/**
 * @author xinwangji@supos.com
 * @date 2021/7/13 10:52
 * @description
 */
public class SaaSApiEntity {


    /**
     * 接口地址
     * 取接口路径的后缀
     * 如：/open-api/auth/v2/oauth2/token
     */
    private String url;

    /**
     * Http Method
     * 如:GET POST DELETE
     */
    private Method method;

    private SaaSApiEntity(){
        //无参构造方法私有化
    }

    public SaaSApiEntity(String url, Method method) {
        this.url = url;
        this.method = method;
    }

    public SaaSApiEntity(SaaSApiEnum suposApiEnum){
        this.url = suposApiEnum.getUrl();
        this.method = suposApiEnum.getMethod();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
