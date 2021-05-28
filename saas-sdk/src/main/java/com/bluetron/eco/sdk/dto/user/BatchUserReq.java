package com.bluetron.eco.sdk.dto.user;

import java.io.Serializable;
import java.util.List;

public class BatchUserReq implements Serializable {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    public BatchUserReq(List<String>list){
        this.list=list;
    }
}
