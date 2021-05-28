package com.bluetron.eco.sdk.dto.org;

import com.bluetron.eco.sdk.dto.common.BaseListRequest;

public class PositionPersonReq extends BaseListRequest {
    String positionCode;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
}
