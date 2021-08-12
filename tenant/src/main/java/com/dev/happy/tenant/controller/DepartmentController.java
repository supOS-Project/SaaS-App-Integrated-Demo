package com.dev.happy.tenant.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dev.happy.tenant.entity.Department;
import com.dev.happy.tenant.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @GetMapping
    public String view() {
        return "manage/department.html";
    }

    @GetMapping("list")
    @ResponseBody
    public PageResult<List<Department>> list(Long page, Long limit, HttpServletRequest request) {
        String tenantId= CookieUtil.getCookie("tenantId",request);
        LambdaQueryWrapper<Department> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Department::getTenantId,tenantId).orderByAsc(Department::getSort,Department::getLayNo);
        IPage<Department> iPage = new Page<>(page, limit);
        departmentService.page(iPage,queryWrapper);
        PageResult<List<Department>> list = new PageResult<>();
        list.setCount(iPage.getTotal());
        list.setData(iPage.getRecords());
        return list;
    }
}
