package com.dev.happy.tenant.vo;

import java.util.List;

public class RoleRes  {
    private Integer roleId;
    private String code;
    private String name;
    private String showName;
    private String description;
    private Boolean underControlled;
    private List<ResourceRes> resources;
    private String createTime;

    public static RoleResBuilder builder() {
        return new RoleResBuilder();
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getShowName() {
        return this.showName;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getUnderControlled() {
        return this.underControlled;
    }

    public List<ResourceRes> getResources() {
        return this.resources;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setShowName(final String showName) {
        this.showName = showName;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setUnderControlled(final Boolean underControlled) {
        this.underControlled = underControlled;
    }

    public void setResources(final List<ResourceRes> resources) {
        this.resources = resources;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "RoleRes(roleId=" + this.getRoleId() + ", code=" + this.getCode() + ", name=" + this.getName() + ", showName=" + this.getShowName() + ", description=" + this.getDescription() + ", underControlled=" + this.getUnderControlled() + ", resources=" + this.getResources() + ", createTime=" + this.getCreateTime() + ")";
    }

    public RoleRes() {
    }

    public RoleRes(final Integer roleId, final String code, final String name, final String showName, final String description, final Boolean underControlled, final List<ResourceRes> resources, final String createTime) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.showName = showName;
        this.description = description;
        this.underControlled = underControlled;
        this.resources = resources;
        this.createTime = createTime;
    }

    public static class RoleResBuilder {
        private Integer roleId;
        private String code;
        private String name;
        private String showName;
        private String description;
        private Boolean underControlled;
        private List<ResourceRes> resources;
        private String createTime;

        RoleResBuilder() {
        }

        public RoleResBuilder roleId(final Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleResBuilder code(final String code) {
            this.code = code;
            return this;
        }

        public RoleResBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public RoleResBuilder showName(final String showName) {
            this.showName = showName;
            return this;
        }

        public RoleResBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public RoleResBuilder underControlled(final Boolean underControlled) {
            this.underControlled = underControlled;
            return this;
        }

        public RoleResBuilder resources(final List<ResourceRes> resources) {
            this.resources = resources;
            return this;
        }

        public RoleResBuilder createTime(final String createTime) {
            this.createTime = createTime;
            return this;
        }

        public RoleRes build() {
            return new RoleRes(this.roleId, this.code, this.name, this.showName, this.description, this.underControlled, this.resources, this.createTime);
        }

        @Override
        public String toString() {
            return "RoleRes.RoleResBuilder(roleId=" + this.roleId + ", code=" + this.code + ", name=" + this.name + ", showName=" + this.showName + ", description=" + this.description + ", underControlled=" + this.underControlled + ", resources=" + this.resources + ", createTime=" + this.createTime + ")";
        }
    }
}
