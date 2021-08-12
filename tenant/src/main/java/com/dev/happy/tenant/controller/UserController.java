package com.dev.happy.tenant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dev.happy.tenant.entity.User;
import com.dev.happy.tenant.service.UserService;
import com.dev.happy.tenant.utils.CookieUtil;
import com.dev.happy.tenant.vo.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public String view() {
        return "manage/user.html";
    }

    @GetMapping("list")
    @ResponseBody
    public PageResult<List<User>> list(Long page, Long limit, HttpServletRequest request) {
        String tenantId= CookieUtil.getCookie("tenantId",request);
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getTenantId,tenantId).orderByAsc(User::getModifyTime);
        IPage<User> iPage = new Page<>(page, limit);
        userService.page(iPage,queryWrapper);
        PageResult<List<User>> list = new PageResult<>();
        list.setCount(iPage.getTotal());
        list.setData(iPage.getRecords());
        return list;
    }
}
