package com.dev.happy.tenant.enums;

public enum TenantStatus {
    /**
     * 租户状态(INITIAL: 租户开通中 ,USING:租户开通成功,订阅中,DEACTIVATED:租户订阅到期暂停,DELETED:租户订阅到期停止,ABNORMAL:租户开通异常)
     */
    INITIAL(0, "INITIAL"),
    USING(1, "USING"),
    DEACTIVATED(2, "DEACTIVATED"),
    DELETED(3, "DELETED"),
    ABNORMAL(4, "ABNORMAL");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    private TenantStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        TenantStatus[] tenantStatuses = TenantStatus.values();
        for (int i = 0; i < tenantStatuses.length; i++) {
            if (code == tenantStatuses[i].code) {
                return tenantStatuses[i].name;
            }
        }
        return TenantStatus.INITIAL.name;
    }
}
