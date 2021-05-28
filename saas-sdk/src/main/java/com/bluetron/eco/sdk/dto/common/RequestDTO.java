

package com.bluetron.eco.sdk.dto.common;

import com.alibaba.fastjson.JSON;

public class RequestDTO {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
