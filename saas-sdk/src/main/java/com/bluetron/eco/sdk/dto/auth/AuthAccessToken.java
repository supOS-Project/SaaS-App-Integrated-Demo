package com.bluetron.eco.sdk.dto.auth;

import java.io.Serializable;

public class AuthAccessToken implements Serializable {
    private static final long serialVersionUID = 4229698091473283894L;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private String companyCode;
    private String personCode;
    private String userType;
    private String username;

    @Override
    public String toString() {
        return "AuthAccessToken(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", expiresIn=" + this.getExpiresIn() + ", companyCode=" + this.getCompanyCode() + ", personCode=" + this.getPersonCode() + ", userType=" + this.getUserType() + ", username=" + this.getUsername() + ")";
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpiresIn(final Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setCompanyCode(final String companyCode) {
        this.companyCode = companyCode;
    }

    public void setPersonCode(final String personCode) {
        this.personCode = personCode;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public String getPersonCode() {
        return this.personCode;
    }

    public String getUserType() {
        return this.userType;
    }

    public String getUsername() {
        return this.username;
    }
}
