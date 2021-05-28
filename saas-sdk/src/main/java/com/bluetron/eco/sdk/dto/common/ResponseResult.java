package com.bluetron.eco.sdk.dto.common;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private static final Integer DEFAULT_SUCCESS_CODE = 200;
    private static final String DEFAULT_SUCCESS_MSG = "操作成功";
    private Integer code;
    private String msg;
    private T data;
    public static <T> ResponseResult SUCCESS() {
        return new ResponseResult(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }
    public static <T> ResponseResult SUCCESS(T data) {
        return new ResponseResult(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }
    public static <T> ResponseResult FAIL(ResultCode errCode) {
        return new ResponseResult(errCode.code(), errCode.message());
    }

    public ResponseResult() {
        this.code = DEFAULT_SUCCESS_CODE;
        this.msg = DEFAULT_SUCCESS_MSG;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
