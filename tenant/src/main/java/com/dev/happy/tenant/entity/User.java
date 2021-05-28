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
        private String email;
        private String phone;
        private Date createTime;
        private Date updateTime;
}
