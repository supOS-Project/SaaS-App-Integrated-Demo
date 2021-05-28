package com.dev.happy.tenant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.mapper.UserMapper;
import com.dev.happy.tenant.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return
     */
    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> query=new LambdaQueryWrapper<>();
        query.eq(User::getName,name).last(" limit 1");
        return baseMapper.selectOne(query);
    }
}
