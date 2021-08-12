package com.dev.happy.tenant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dev.happy.tenant.entity.ext.DictInfo;
import com.dev.happy.tenant.entity.ext.UserInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * supOS 人员定义
 *
 * @author caonuoqi@supos.com
 */
@TableName(value = "t_person", autoResultMap = true)
public class Person implements Serializable {

    private String code;
    private String name;
    private Integer valid;
    private Date modifyTime;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private UserInfo user;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private DictInfo gender;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private DictInfo mainPosition;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<DictInfo> positions;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<DictInfo> companies;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<DictInfo> departments;
    private Date entryDate;
    private String title;
    private String education;
    private String qualification;
    private String major;
    private String idNumber;
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

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public DictInfo getGender() {
        return gender;
    }

    public void setGender(DictInfo gender) {
        this.gender = gender;
    }

    public DictInfo getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(DictInfo mainPosition) {
        this.mainPosition = mainPosition;
    }

    public List<DictInfo> getPositions() {
        return positions;
    }

    public void setPositions(List<DictInfo> positions) {
        this.positions = positions;
    }

    public List<DictInfo> getCompanies() {
        return companies;
    }

    public void setCompanies(List<DictInfo> companies) {
        this.companies = companies;
    }

    public List<DictInfo> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DictInfo> departments) {
        this.departments = departments;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
