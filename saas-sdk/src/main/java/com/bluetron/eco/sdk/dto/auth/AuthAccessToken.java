package com.bluetron.eco.sdk.dto.auth;


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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
