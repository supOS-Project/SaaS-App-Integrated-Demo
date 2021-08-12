package com.dev.happy.tenant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dev.happy.tenant.entity.ext.DictInfo;
import com.dev.happy.tenant.entity.ext.ShowInfo;

import java.util.Date;
import java.util.List;

/**
 * supOS 部门定义
 *
 * @author caonuoqi@supos.com
 */
@TableName(value = "t_department", autoResultMap = true)
public class Department {
    private String code;
    private String name;
    private Integer valid;
    private Date modifyTime;
    private String description;
    private String fullPath;
    private Integer layNo;
    private Integer sort;
    private String parentCode;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private DictInfo deptType;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private ShowInfo company;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<DictInfo> positions;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<DictInfo> managers;
    private String tenantId;
    @TableId(type = IdType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getLayNo() {
        return layNo;
    }

    public void setLayNo(Integer layNo) {
        this.layNo = layNo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public DictInfo getDeptType() {
        return deptType;
    }

    public void setDeptType(DictInfo deptType) {
        this.deptType = deptType;
    }

    public ShowInfo getCompany() {
        return company;
    }

    public void setCompany(ShowInfo company) {
        this.company = company;
    }

    public List<DictInfo> getPositions() {
        return positions;
    }

    public void setPositions(List<DictInfo> positions) {
        this.positions = positions;
    }

    public List<DictInfo> getManagers() {
        return managers;
    }

    public void setManagers(List<DictInfo> managers) {
        this.managers = managers;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
