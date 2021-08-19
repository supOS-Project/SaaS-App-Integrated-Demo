package com.bluetron.eco.sdk.dto.tenant;

import java.io.Serializable;

/**
 * 租户续期入参
 * @author caonuoqi@supos.com
 */
public class RenewAppTenantReqDTO implements Serializable {
    private String tenantId;
    private String endDate;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    /**
     * 拼接签名核验串
     * @return
     */
    public String createSignString(){
       StringBuilder builder = new StringBuilder();
       builder.append("appId=").append(appId)
               .append("&endDate=").append(endDate)
               .append("&randomNumber=").append(randomNumber)
               .append("&tenantId=").append(tenantId)
               .append("&timeStamp=").append(timeStamp);
       return builder.toString();
    }
    @Override
    public String toString() {
        return "RenewAppTenantReqDTO{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", sign='" + sign + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
