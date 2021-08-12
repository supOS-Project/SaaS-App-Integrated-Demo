package com.bluetron.eco.sdk.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Setter
@Getter
public class AuthAccessToken  {

    private static final long serialVersionUID = 4229698091473283894L;

    /**
     * 凭证
     */
    private String accessToken;
    /**
     * 刷新交换凭证
     */
    private String refreshToken;
    /**
     * 过期时间（毫秒）
     */
    private Long expiresIn;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 人员编码
     */
    private String personCode;

    /**
     * 用户类型 0 普通用户,1系统管理员
     */
    private String userType;

    /**
     * 用户名
     */
    private String username;


}
