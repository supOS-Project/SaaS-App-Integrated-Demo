package com.dev.happy.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.happy.tenant.entity.User;

public interface UserService extends IService<User> {
    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getByName(String name);
}
