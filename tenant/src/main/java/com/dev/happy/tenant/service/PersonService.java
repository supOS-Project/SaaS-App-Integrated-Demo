package com.dev.happy.tenant.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bluetron.eco.sdk.dto.ActionType;
import com.dev.happy.tenant.entity.Person;
import com.dev.happy.tenant.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService extends ServiceImpl<PersonMapper, Person> {
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
            List<Person> personList=new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                Person person=body.getObject(i,Person.class);
                person.setTenantId(tenantId);
                personList.add(person);
            }
            saveBatch(personList);
        }
    }

    private void updateHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                Person person=body.getObject(i,Person.class);
                LambdaQueryWrapper<Person> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Person::getTenantId,tenantId).eq(Person::getCode,person.getCode());
                baseMapper.update(person,queryWrapper);
            }
        }
    }

    private void deleteHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                JSONObject tmp = body.getJSONObject(i);
                LambdaQueryWrapper<Person> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Person::getTenantId,tenantId).eq(Person::getCode,tmp.getString("code"));
                baseMapper.delete(queryWrapper);
            }
        }
    }
}
