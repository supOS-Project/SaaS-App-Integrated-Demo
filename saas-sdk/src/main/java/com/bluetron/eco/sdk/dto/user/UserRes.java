//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bluetron.eco.sdk.dto.user;

import com.bluetron.eco.sdk.dto.common.RequestDTO;
import com.bluetron.eco.sdk.dto.role.RoleRes;

import java.util.List;

public class UserRes extends RequestDTO {
    private String username;
    private String userDesc;
    private Integer accountType;
    private Integer lockStatus;
    private String personCode;
    private String personName;
    private String modifyTime;
    private String createTime;
    private List<RoleRes> userRoleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<RoleRes> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<RoleRes> userRoleList) {
        this.userRoleList = userRoleList;
    }
}
