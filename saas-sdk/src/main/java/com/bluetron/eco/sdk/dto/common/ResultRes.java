package com.bluetron.eco.sdk.dto.common;

import cn.hutool.json.JSONUtil;

import java.util.List;

public class ResultRes<T> {
    private PageRes pagination;
    private List<T> list;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    public static <T> ResultRes.ResultResBuilder<T> builder() {
        return new ResultRes.ResultResBuilder();
    }

    public PageRes getPagination() {
        return this.pagination;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setPagination(final PageRes pagination) {
        this.pagination = pagination;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public ResultRes() {
    }

    public ResultRes(final PageRes pagination, final List<T> list) {
        this.pagination = pagination;
        this.list = list;
    }

    public static class ResultResBuilder<T> {
        private PageRes pagination;
        private List<T> list;

        ResultResBuilder() {
        }

        public ResultRes.ResultResBuilder<T> pagination(final PageRes pagination) {
            this.pagination = pagination;
            return this;
        }

        public ResultRes.ResultResBuilder<T> list(final List<T> list) {
            this.list = list;
            return this;
        }

        public ResultRes<T> build() {
            return new ResultRes(this.pagination, this.list);
        }

        @Override
        public String toString() {
            return "ResultRes.ResultResBuilder(pagination=" + this.pagination + ", list=" + this.list + ")";
        }
    }
}
