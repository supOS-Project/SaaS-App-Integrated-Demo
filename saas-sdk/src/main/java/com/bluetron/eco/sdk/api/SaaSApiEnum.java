package com.bluetron.eco.sdk.api;

import cn.hutool.http.Method;

public enum SaaSApiEnum {
    USER_ADD("用户新增", "/auth/v2/users", Method.POST),
    USER_LISTS("分页获取用户列表", "/auth/v2/users", Method.GET),
//    USER_BATCH_DELETE("批量删除用户", "/auth/v2/users", Method.DELETE),
    USER_BATCH_DELETE("批量删除用户","/supos/auth/v2/delete/users",Method.POST),
    USER_DETAIL("用户详情", "/auth/v2/users/%s", Method.GET),
    USER_UPDATE("修改用户", "/auth/v2/users/%s", Method.PUT),
    USER_BIND_ROLE("用户绑定角色", "/auth/v2/users/%s/role", Method.POST),
    USER_UNBIND_ROLE("用户解除绑定角色", "/auth/v2/users/%s/role", Method.PUT),
    COMPANY_LIST("公司列表", "/organization/v2/companies", Method.GET),
    COMPANY_DETAIL("公司详情", "/organization/v2/companies/%s", Method.GET),
    DEPARTMENTS_LIST("部门列表", "/organization/v2/departments", Method.GET),
    DEPARTMENTS_DETAIL("部门详情", "/organization/v2/departments/%s", Method.GET),
    POSITION_LIST("岗位列表", "/organization/v2/positions", Method.GET),
    POSITION_DETAIL("岗位详情", "/organization/v2/positions/%s", Method.GET),
    PERSON_LIST("人员列表", "/organization/v2/persons", Method.GET),
    PERSON_DETAIL("人员详情", "/organization/v2/persons/%s", Method.GET),
    PERSON_LIST_BY_COMPANY("查询指定编码的公司的人员列表", "/organization/v2/companies/%s/persons", Method.GET),
    PERSON_LIST_BY_DEPARTMENTS("查询指定编码的部门的人员列表", "/organization/v2/departments/%s/persons", Method.GET),
    PERSON_LIST_BY_POSITION("查询指定编码的岗位的人员列表", "/organization/v2/positions/%s/persons", Method.GET),
    POSITION_LIST_BY_COMPANY("查询指定编码的公司的岗位列表", "/companies/%s/positions", Method.GET),
    DEPARTMENTS_LIST_BY_COMPANY("查询指定编码的公司的部门列表", "/companies/%s/departments", Method.GET),
    NOTIFICATION_SEND_MESSAGE("消息发送", "/p/notification/v2/messages", Method.POST),
    IGNORE("IGNORE", "IGNORE", Method.PUT);

    private final String name;
    private String url;
    private final Method method;

    public void setUrl(String newUrl) {
        this.url = newUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public Method getMethod() {
        return this.method;
    }

    private SaaSApiEnum(final String name, final String url, final Method method) {
        this.name = name;
        this.url = url;
        this.method = method;
    }
}
