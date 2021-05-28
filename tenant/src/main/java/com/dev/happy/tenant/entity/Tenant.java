package com.dev.happy.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@TableName("t_tenant")
public class Tenant implements Serializable {
    private Long id;
    private String tenantName;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private String tenantId;
    private String instanceId;
    private String appId;
    private String instanceName;
    private String region;
}
