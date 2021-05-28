

package com.bluetron.eco.sdk.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;

public class PageRes {
    @JSONField(
            alternateNames = {"current", "pageIndex", "page"}
    )
    @JsonAlias({"current", "pageIndex", "page"})
    private Integer current;
    @JSONField(
            alternateNames = {"size", "pagesize"}
    )
    @JsonAlias({"size", "pagesize"})
    private Integer pageSize;
    private Integer total;

    public static PageRes.PageResBuilder builder() {
        return new PageRes.PageResBuilder();
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotal() {
        return this.total;
    }

    @JsonAlias({"current", "pageIndex", "page"})
    public void setCurrent(final Integer current) {
        this.current = current;
    }

    @JsonAlias({"size", "pagesize"})
    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "PageRes(current=" + this.getCurrent() + ", pageSize=" + this.getPageSize() + ", total=" + this.getTotal() + ")";
    }

    public PageRes() {
    }

    public PageRes(final Integer current, final Integer pageSize, final Integer total) {
        this.current = current;
        this.pageSize = pageSize;
        this.total = total;
    }

    public static class PageResBuilder {
        private Integer current;
        private Integer pageSize;
        private Integer total;

        PageResBuilder() {
        }

        @JsonAlias({"current", "pageIndex", "page"})
        public PageRes.PageResBuilder current(final Integer current) {
            this.current = current;
            return this;
        }

        @JsonAlias({"size", "pagesize"})
        public PageRes.PageResBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageRes.PageResBuilder total(final Integer total) {
            this.total = total;
            return this;
        }

        public PageRes build() {
            return new PageRes(this.current, this.pageSize, this.total);
        }

        @Override
        public String toString() {
            return "PageRes.PageResBuilder(current=" + this.current + ", pageSize=" + this.pageSize + ", total=" + this.total + ")";
        }
    }
}
