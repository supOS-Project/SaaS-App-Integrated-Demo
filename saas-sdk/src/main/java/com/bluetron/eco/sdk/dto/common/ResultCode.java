package com.bluetron.eco.sdk.dto.common;

public enum ResultCode {
    SUCCESS(1, "成功"),
    FAIL(-1, "失败"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    RESULT_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
    PERMISSION_NO_ACCESS(70001, "无访问权限"),
    TENANT_EXIST(100039,"租户已经开通，请勿重复开通"),
    SIGN_ERROR(20000,"签名错误"),
    EMPTY_PARAM_ERROR(20001,"参数为空"),
    INVALID_INPUT(400, "Invalid input"),
    INVALID_CREDENTIAL(403, "Invalid credential"),
    TENANT_NOT_EXIST(100040,"租户不存在");
    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        ResultCode[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ResultCode item = var1[var3];
            if (item.name().equals(name)) {
                return item.message;
            }
        }

        return name;
    }

    public static Integer getCode(String name) {
        ResultCode[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ResultCode item = var1[var3];
            if (item.name().equals(name)) {
                return item.code;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
