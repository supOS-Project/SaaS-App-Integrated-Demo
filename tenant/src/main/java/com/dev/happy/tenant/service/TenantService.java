package com.dev.happy.tenant.service;

import com.dev.happy.tenant.entity.Tenant;
import com.bluetron.eco.sdk.dto.tenant.OpeningAppTenantReqDTO;
import com.bluetron.eco.sdk.dto.tenant.OpeningAppTenantResDTO;
import com.bluetron.eco.sdk.dto.tenant.RenewAppTenantReqDTO;

public interface TenantService{
    OpeningAppTenantResDTO openTenant(OpeningAppTenantReqDTO param);
    Tenant getTenant(String instanceId, String appId,String tenantId);
    void renew(RenewAppTenantReqDTO param,Tenant tenant);
    void stop(Tenant tenant);

}
