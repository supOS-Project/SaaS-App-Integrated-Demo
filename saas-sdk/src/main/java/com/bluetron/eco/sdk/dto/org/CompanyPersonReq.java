package com.bluetron.eco.sdk.dto.org;

import com.bluetron.eco.sdk.dto.common.BaseListRequest;

public class CompanyPersonReq extends BaseListRequest {
    String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
