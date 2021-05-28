package com.bluetron.eco.sdk.dto.tenant;

import java.io.Serializable;

/**
 * 开通租户的入参
 * @author caonuoqi@supos.com
 */

public class OpeningAppTenantReqDTO implements Serializable {
    /**
     * ess 实例id
     */
    private String instanceId;
    /**
     * 租户开通开始时间
     */
    private String startDate;
    /**
     * 租户开通结束时间
     */
    private String endDate;
    /**
     * ess 实例名称
     */
    private String instanceName;
    /**
     * 实例所在区域
     */
    private String region;
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

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String createSignString() {
        StringBuilder builder = new StringBuilder();
        builder.append("appId=").append(appId)
                .append("&endDate=").append(endDate)
                .append("&instanceId=").append(instanceId)
                .append("&instanceName=").append(instanceName)
                .append("&randomNumber=").append(randomNumber)
                .append("&region=").append(region)
                .append("&startDate=").append(startDate)
                .append("&timeStamp=").append(timeStamp);
        return builder.toString();
    }

    @Override
    public String toString() {
        return "OpeningAppTenantReqDTO{" +
                "instanceId='" + instanceId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", region='" + region + '\'' +
                ", appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
