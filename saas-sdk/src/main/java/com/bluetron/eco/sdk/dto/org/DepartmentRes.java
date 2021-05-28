package com.bluetron.eco.sdk.dto.org;

import java.util.List;

public class DepartmentRes {
    private String parentCode;
    private String code;
    private String name;
    private DepartmentRes.DeptType deptType;
    private String description;
    private String fullPath;
    private Integer layNo;
    private Float sort;
    private Integer valid;
    private String modifyTime;
    private CompanyRes company;
    private List<DepartmentRes.Manage> managers;

    public String getParentCode() {
        return this.parentCode;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public DepartmentRes.DeptType getDeptType() {
        return this.deptType;
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

    public CompanyRes getCompany() {
        return this.company;
    }

    public List<DepartmentRes.Manage> getManagers() {
        return this.managers;
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

    public void setDeptType(final DepartmentRes.DeptType deptType) {
        this.deptType = deptType;
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

    public void setCompany(final CompanyRes company) {
        this.company = company;
    }

    public void setManagers(final List<DepartmentRes.Manage> managers) {
        this.managers = managers;
    }



    @Override
    public String toString() {
        return "DepartmentRes(parentCode=" + this.getParentCode() + ", code=" + this.getCode() + ", name=" + this.getName() + ", deptType=" + this.getDeptType() + ", description=" + this.getDescription() + ", fullPath=" + this.getFullPath() + ", layNo=" + this.getLayNo() + ", sort=" + this.getSort() + ", valid=" + this.getValid() + ", modifyTime=" + this.getModifyTime() + ", company=" + this.getCompany() + ", managers=" + this.getManagers() + ")";
    }

    public DepartmentRes() {
    }

    public DepartmentRes(final String parentCode, final String code, final String name, final DepartmentRes.DeptType deptType, final String description, final String fullPath, final Integer layNo, final Float sort, final Integer valid, final String modifyTime, final CompanyRes company, final List<DepartmentRes.Manage> managers) {
        this.parentCode = parentCode;
        this.code = code;
        this.name = name;
        this.deptType = deptType;
        this.description = description;
        this.fullPath = fullPath;
        this.layNo = layNo;
        this.sort = sort;
        this.valid = valid;
        this.modifyTime = modifyTime;
        this.company = company;
        this.managers = managers;
    }

    private static class Manage {
        String name;
        String code;

        public Manage() {
        }

        public String getName() {
            return this.name;
        }

        public String getCode() {
            return this.code;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setCode(final String code) {
            this.code = code;
        }

    }

    private static class DeptType {
        String code;
        String name;

        public DeptType() {
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public void setCode(final String code) {
            this.code = code;
        }

        public void setName(final String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "DepartmentRes.DeptType(code=" + this.getCode() + ", name=" + this.getName() + ")";
        }
    }
}
