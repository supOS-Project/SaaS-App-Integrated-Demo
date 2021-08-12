package com.dev.happy.tenant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dev.happy.tenant.entity.Company;
import com.dev.happy.tenant.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @GetMapping
    public String view() {
        return "manage/company.html";
    }

    @GetMapping("list")
    @ResponseBody
    public PageResult<List<Company>> list(Long page, Long limit, HttpServletRequest request) {
        String tenantId= CookieUtil.getCookie("tenantId",request);
        LambdaQueryWrapper<Company> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Company::getTenantId,tenantId).orderByAsc(Company::getSort,Company::getLayNo);
        IPage<Company> iPage = new Page<>(page, limit);
        companyService.page(iPage,queryWrapper);
        PageResult<List<Company>> list = new PageResult<>();
        list.setCount(iPage.getTotal());
        list.setData(iPage.getRecords());
        return list;
    }
}
