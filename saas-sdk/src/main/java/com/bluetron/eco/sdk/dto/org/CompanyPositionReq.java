package com.bluetron.eco.sdk.dto.org;

import java.io.Serializable;

/**
 * @author caonuoqi@supos.com
 */
public class CompanyPositionReq implements Serializable {
    String companyCode;
    /**
     * 当前页
     */
    Integer current=1;
    /**
     * 一页显示的最大记录数
     */
    Integer pageSize=20;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
