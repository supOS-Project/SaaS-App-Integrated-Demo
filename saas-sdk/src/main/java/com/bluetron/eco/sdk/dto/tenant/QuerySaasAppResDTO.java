package com.bluetron.eco.sdk.dto.tenant;

/**
 * 租户状态返回结果
 * @author caonuoqi@supos.com
 */
public class QuerySaasAppResDTO {
    private String tenantId;
    private String tenantUrl;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantUrl() {
        return tenantUrl;
    }

    public void setTenantUrl(String tenantUrl) {
        this.tenantUrl = tenantUrl;
    }
}
