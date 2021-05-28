package com.bluetron.eco.sdk.dto.user;

import com.bluetron.eco.sdk.dto.common.RequestDTO;

public class GetAllUsersReq extends RequestDTO {
    private String keywords;
    private String staffCode;
    private Integer pageIndex;
    private Integer pageSize;
    private String companyCode;
    private String roleCode;
    private String modifyTime;

    public GetAllUsersReq() {
        this.pageIndex = 1;
        this.pageSize = 20;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    public void setPageIndex(final Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCompanyCode(final String companyCode) {
        this.companyCode = companyCode;
    }

    public void setRoleCode(final String roleCode) {
        this.roleCode = roleCode;
    }

    public void setModifyTime(final String modifyTime) {
        this.modifyTime = modifyTime;
    }


    @Override
    public String toString() {
        return "GetAllUsersReq(keywords=" + this.getKeywords() + ", staffCode=" + this.getStaffCode() + ", pageIndex=" + this.getPageIndex() + ", pageSize=" + this.getPageSize() + ", companyCode=" + this.getCompanyCode() + ", roleCode=" + this.getRoleCode() + ", modifyTime=" + this.getModifyTime() + ")";
    }

    public GetAllUsersReq(final String keywords, final String staffCode, final Integer pageIndex, final Integer pageSize, final String companyCode, final String roleCode, final String modifyTime) {
        this.keywords = keywords;
        this.staffCode = staffCode;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.companyCode = companyCode;
        this.roleCode = roleCode;
        this.modifyTime = modifyTime;
    }

}
