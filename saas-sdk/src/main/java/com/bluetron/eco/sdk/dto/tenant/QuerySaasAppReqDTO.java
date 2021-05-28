package com.bluetron.eco.sdk.dto.tenant;

import java.io.Serializable;

/**
 * 查询租户相关的入参
 * @author caonuoqi@supos.com
 */
public class QuerySaasAppReqDTO implements Serializable {
    String tenantId;
    /**
     * appId
     */
    String appId;
    /**
     * 时间戳
     */
    String timeStamp;
    /**
     * 随机数
     */
    String randomNumber;
    /**
     * 签名
     */
    String sign;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String createSignString(){
        StringBuilder builder = new StringBuilder();
        builder.append("appId=").append(appId)
                .append("&randomNumber=").append(randomNumber)
                .append("&tenantId=").append(tenantId)
                .append("&timeStamp=").append(timeStamp);
        return builder.toString();
    }
    @Override
    public String toString() {
        return "QuerySaasAppReqDTO{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", sign='" + sign + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
