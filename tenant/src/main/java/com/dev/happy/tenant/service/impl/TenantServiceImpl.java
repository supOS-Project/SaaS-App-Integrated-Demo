package com.dev.happy.tenant.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.enums.TenantStatus;
import com.dev.happy.tenant.mapper.TenantMapper;
import com.dev.happy.tenant.service.TenantService;
import com.bluetron.eco.sdk.dto.tenant.OpeningAppTenantReqDTO;
import com.bluetron.eco.sdk.dto.tenant.OpeningAppTenantResDTO;
import com.bluetron.eco.sdk.dto.tenant.RenewAppTenantReqDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantMapper;

    @Override
    public OpeningAppTenantResDTO openTenant(OpeningAppTenantReqDTO param) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(UUID.randomUUID().toString().replaceAll("-","").toLowerCase());
        BeanUtils.copyProperties(param, tenant);
        Date startTime = DateUtil.parse(param.getStartDate(), DatePattern.NORM_DATETIME_PATTERN);
        Date endTime = DateUtil.parse(param.getEndDate(), DatePattern.NORM_DATETIME_PATTERN);
        tenant.setStartTime(startTime);
        tenant.setEndTime(endTime);
        tenant.setCreateTime(new Date());
        tenant.setUpdateTime(new Date());
        tenant.setStatus(TenantStatus.USING.getCode());
        tenantMapper.insert(tenant);
        OpeningAppTenantResDTO res = new OpeningAppTenantResDTO();
        res.setTenantId(tenant.getTenantId());
        return res;
    }
    @Override
    public Tenant getTenant(String instanceId, String appId,String tenantId) {
        LambdaQueryWrapper<Tenant> lambdaQuery = Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(instanceId)){
            lambdaQuery.eq(Tenant::getInstanceId, instanceId);
        }
        if(StringUtils.isNotBlank(appId)){
            lambdaQuery.eq(Tenant::getAppId, appId);
        }
        if(StringUtils.isNotBlank(tenantId)){
            lambdaQuery.eq(Tenant::getTenantId, tenantId);
        }
        lambdaQuery.last(" limit 1");
        return tenantMapper.selectOne(lambdaQuery);
    }

    @Override
    public void renew(RenewAppTenantReqDTO param,Tenant tenant) {
        Date endTime = DateUtil.parse(param.getEndDate(), DatePattern.NORM_DATETIME_PATTERN);
        Tenant update=new Tenant();
        update.setId(tenant.getId());
        update.setEndTime(endTime);
        update.setStatus(TenantStatus.USING.getCode());
        tenantMapper.updateById(update);
    }
    @Override
    public void stop(Tenant tenant) {
        Tenant update=new Tenant();
        update.setId(tenant.getId());
        update.setStatus(TenantStatus.DELETED.getCode());
        tenantMapper.updateById(update);
    }
}
