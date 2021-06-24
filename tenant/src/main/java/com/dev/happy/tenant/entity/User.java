package com.dev.happy.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@TableName("t_user")
public class User implements Serializable {
        private Long id;
        private String name;
        private Date createTime;
        private Date updateTime;
        private String tenantId;
        private Integer accountType;
        private String personCode;
        private String personName;
}
