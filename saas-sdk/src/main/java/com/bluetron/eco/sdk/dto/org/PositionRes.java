package com.bluetron.eco.sdk.dto.org;

import com.bluetron.eco.sdk.dto.role.RoleRes;

public class PositionRes {
    private String parentCode;
    private String code;
    private String name;
    private CompanyRes company;
    private DepartmentRes department;
    private String description;
    private String fullPath;
    private Integer layNo;
    private Float sort;
    private Integer valid;
    private String modifyTime;
    private RoleRes roleRes;

    public String getParentCode() {
        return this.parentCode;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public CompanyRes getCompany() {
        return this.company;
    }

    public DepartmentRes getDepartment() {
        return this.department;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public Integer getLayNo() {
        return this.layNo;
    }

    public Float getSort() {
        return this.sort;
    }

    public Integer getValid() {
        return this.valid;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

    public RoleRes getRoleRes() {
        return this.roleRes;
    }

    public void setParentCode(final String parentCode) {
        this.parentCode = parentCode;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCompany(final CompanyRes company) {
        this.company = company;
    }

    public void setDepartment(final DepartmentRes department) {
        this.department = department;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setFullPath(final String fullPath) {
        this.fullPath = fullPath;
    }

    public void setLayNo(final Integer layNo) {
        this.layNo = layNo;
    }

    public void setSort(final Float sort) {
        this.sort = sort;
    }

    public void setValid(final Integer valid) {
        this.valid = valid;
    }

    public void setModifyTime(final String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setRoleRes(final RoleRes roleRes) {
        this.roleRes = roleRes;
    }
}
