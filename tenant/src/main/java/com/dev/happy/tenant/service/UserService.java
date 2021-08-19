package com.dev.happy.tenant.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluetron.eco.sdk.dto.ActionType;
import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.entity.ext.DictInfo;
import com.dev.happy.tenant.mapper.UserMapper;
import com.dev.happy.tenant.vo.UserRes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return
     */
    public User getByTenantIdAndName(String tenantId, String name) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getName, name)
                .eq(User::getTenantId, tenantId)
                .last(" limit 1");
        return baseMapper.selectOne(query);
    }

    public User saveAndGet(String tenantId, UserRes userRes) {
        User user = new User();
        user.setTenantId(tenantId);
        user.setName(userRes.getUsername());
        user.setAccountType(userRes.getAccountType());
        user.setPersonCode(userRes.getPersonCode());
        user.setPersonName(userRes.getPersonName());
        baseMapper.insert(user);
        return user;
    }

    public void messageHandle(String tenantId, ActionType actionType, JSONArray body) {
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
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                JSONObject u = body.getJSONObject(i);
                User user = new User();
                user.setName(u.getString("name"));
                DictInfo person = u.getObject("person", DictInfo.class);
                user.setPersonCode(person.getCode());
                user.setPersonName(person.getName());
                user.setTenantId(tenantId);
                userList.add(user);
            }
            saveBatch(userList);
        }
    }

    private void updateHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                JSONObject u = body.getJSONObject(i);
                User user = new User();
                user.setName(u.getString("name"));
                DictInfo person = u.getObject("person", DictInfo.class);
                user.setPersonCode(person.getCode());
                user.setPersonName(person.getName());
                LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(User::getTenantId, tenantId).eq(User::getName, user.getName());
                baseMapper.update(user, queryWrapper);
            }
        }
    }

    private void deleteHandle(String tenantId, JSONArray body) {
        if (!body.isEmpty()) {
            for (int i = 0; i < body.size(); i++) {
                JSONObject tmp = body.getJSONObject(i);
                LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(User::getTenantId, tenantId).eq(User::getName, tmp.getString("name"));
                baseMapper.delete(queryWrapper);
            }
        }
    }
}
