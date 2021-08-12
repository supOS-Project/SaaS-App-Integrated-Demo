package com.dev.happy.tenant.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluetron.eco.sdk.dto.ActionType;
import com.dev.happy.tenant.entity.Company;
import com.dev.happy.tenant.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService extends ServiceImpl<CompanyMapper, Company> {
    public List<String> companyCodeList(String tenantId) {
        return baseMapper.companyCodeList(tenantId);
    }

    public void messageHandle(String tenantId,ActionType actionType, JSONArray body) {
        switch (actionType.getEvent()) {
            case ActionType.EVENT_CREATE:
                createHandle(tenantId, body);
                break;
            case ActionType.EVENT_UPDATE:
                updateHandle(tenantId, body);
                break;
            case ActionType.EVENT_DELETE:
                deleteHandle(tenantId, body);
                break;
        }
    }

    private void createHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            List<Company> companyList=new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                Company company=body.getObject(i,Company.class);
                company.setTenantId(tenantId);
                companyList.add(company);
            }
            saveBatch(companyList);
        }
    }

    private void updateHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                Company company=body.getObject(i,Company.class);
                LambdaQueryWrapper<Company> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Company::getTenantId,tenantId).eq(Company::getCode,company.getCode());
                baseMapper.update(company,queryWrapper);
            }
        }
    }

    private void deleteHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                JSONObject tmp = body.getJSONObject(i);
                String companyCode=tmp.getString("code");
                LambdaQueryWrapper<Company> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Company::getTenantId,tenantId).eq(Company::getCode,companyCode);
                baseMapper.delete(queryWrapper);
            }
        }
    }
}
