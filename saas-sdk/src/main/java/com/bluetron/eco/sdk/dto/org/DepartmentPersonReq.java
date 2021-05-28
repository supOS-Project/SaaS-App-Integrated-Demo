package com.bluetron.eco.sdk.dto.org;

import com.bluetron.eco.sdk.dto.common.BaseListRequest;

public class DepartmentPersonReq extends BaseListRequest {
    String departmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
