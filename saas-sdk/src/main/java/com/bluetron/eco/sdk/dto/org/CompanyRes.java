package com.bluetron.eco.sdk.dto.org;

import java.util.List;

public class CompanyRes {
    private String code;
    private String parentCode;
    private String fullName;
    private String shortName;
    private String description;
    private List<String> tags;
    private String fullPath;
    private Integer layNo;
    private Float sort;
    private Integer valid;
    private String modifyTime;

    public CompanyRes() {
    }

    public CompanyRes(final String code, final String parentCode, final String fullName, final String shortName, final String description, final List<String> tags, final String fullPath, final Integer layNo, final Float sort, final Integer valid, final String modifyTime) {
        this.code = code;
        this.parentCode = parentCode;
        this.fullName = fullName;
        this.shortName = shortName;
        this.description = description;
        this.tags = tags;
        this.fullPath = fullPath;
        this.layNo = layNo;
        this.sort = sort;
        this.valid = valid;
        this.modifyTime = modifyTime;
    }

    public String getCode() {
        return this.code;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getTags() {
        return this.tags;
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

    public void setCode(final String code) {
        this.code = code;
    }

    public void setParentCode(final String parentCode) {
        this.parentCode = parentCode;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setTags(final List<String> tags) {
        this.tags = tags;
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
}
