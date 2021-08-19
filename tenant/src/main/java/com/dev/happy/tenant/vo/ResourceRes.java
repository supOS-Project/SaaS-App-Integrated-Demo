package com.dev.happy.tenant.vo;

import java.io.Serializable;
import java.util.List;

public class ResourceRes implements Serializable {
    private String resourceId;
    private String parentId;
    private String name;
    private String platformType;
    private Double resourceOrder;
    private String resource;
    private String resourceCode;
    private String resourceFunctionType;
    private String resourceType;
    private Integer hide;
    private String source;
    private String description;
    private String createTime;
    private List<ResourceRes> childResources;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public Double getResourceOrder() {
        return resourceOrder;
    }

    public void setResourceOrder(Double resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceFunctionType() {
        return resourceFunctionType;
    }

    public void setResourceFunctionType(String resourceFunctionType) {
        this.resourceFunctionType = resourceFunctionType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ResourceRes> getChildResources() {
        return childResources;
    }

    public void setChildResources(List<ResourceRes> childResources) {
        this.childResources = childResources;
    }
}
