package com.dev.happy.tenant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluetron.eco.sdk.dto.user.UserRes;
import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.mapper.UserMapper;
import com.dev.happy.tenant.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return
     */
    @Override
    public User getByTenantIdAndName(String tenantId, String name) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getName, name)
             .eq(User::getTenantId, tenantId)
             .last(" limit 1");
        return baseMapper.selectOne(query);
    }
    @Override
   public User saveAndGet(String tenantId,UserRes userRes){
        User user=new User();
        user.setTenantId(tenantId);
        user.setName(userRes.getUsername());
        user.setAccountType(userRes.getAccountType());
        user.setPersonCode(userRes.getPersonCode());
        user.setPersonName(userRes.getPersonName());
        baseMapper.insert(user);
        return user;
    }
}
