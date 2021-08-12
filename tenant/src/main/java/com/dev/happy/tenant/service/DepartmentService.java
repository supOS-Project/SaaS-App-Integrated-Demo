package com.dev.happy.tenant.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluetron.eco.sdk.dto.ActionType;
import com.dev.happy.tenant.entity.Department;
import com.dev.happy.tenant.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department>  {
    public void messageHandle(String tenantId,ActionType actionType,  JSONArray body) {
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
            List<Department> departmentList=new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                Department department=body.getObject(i,Department.class);
                department.setTenantId(tenantId);
                departmentList.add(department);
            }
            saveBatch(departmentList);
        }
    }

    private void updateHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                Department department=body.getObject(i,Department.class);
                LambdaQueryWrapper<Department> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Department::getTenantId,tenantId).eq(Department::getCode,department.getCode());
                baseMapper.update(department,queryWrapper);
            }
        }
    }

    private void deleteHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                JSONObject tmp = body.getJSONObject(i);
                String departmentCode=tmp.getString("code");
                LambdaQueryWrapper<Department> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Department::getTenantId,tenantId).eq(Department::getCode,departmentCode);
                baseMapper.delete(queryWrapper);
            }
        }
    }
}
