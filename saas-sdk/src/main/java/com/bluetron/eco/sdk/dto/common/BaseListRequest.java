package com.bluetron.eco.sdk.dto.common;

/**
 * open-api列表查询基础类
 * @author caonuoqi@supos.com
 */
public class BaseListRequest {
    /**
     * 当前页
     */
    Integer current=1;
    /**
     * 一页显示的最大记录数
     */
    Integer pageSize=20;
    /**
     * 修改时间,填写则获取大于modifyTime时间被修改过的数据
     */
    String modifyTime;

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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
