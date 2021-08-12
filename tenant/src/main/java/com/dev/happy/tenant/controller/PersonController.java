package com.dev.happy.tenant.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dev.happy.tenant.entity.Person;
import com.dev.happy.tenant.service.PersonService;
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
@RequestMapping("/person")
public class PersonController {
    @Resource
    private PersonService personService;

    @GetMapping
    public String view() {
        return "manage/person.html";
    }

    @GetMapping("list")
    @ResponseBody
    public PageResult<List<Person>> list(Long page, Long limit, HttpServletRequest request) {
        String tenantId= CookieUtil.getCookie("tenantId",request);
        LambdaQueryWrapper<Person> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getTenantId,tenantId).orderByAsc(Person::getModifyTime);
        IPage<Person> iPage = new Page<>(page, limit);
        personService.page(iPage,queryWrapper);
        PageResult<List<Person>> list = new PageResult<>();
        list.setCount(iPage.getTotal());
        list.setData(iPage.getRecords());
        return list;
    }
}
