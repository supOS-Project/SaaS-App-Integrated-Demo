package com.bluetron.eco.sdk.dto.common;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    private String message;
    private T data;
    private Integer httpCode;
    private Integer code;
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public Integer getCode() {
        return this.code;
    }



    public void setData(final T data) {
        this.data = data;
    }

    public void setHttpCode(final Integer httpCode) {
        this.httpCode = httpCode;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public ApiResponse(final String message, final T data, final Integer httpCode, final Integer code) {
        this.message = message;
        this.data = data;
        this.httpCode = httpCode;
        this.code = code;
    }
    public ApiResponse(final String message, final T data, final Integer httpCode, final Integer code,Boolean success) {
        this.message = message;
        this.data = data;
        this.httpCode = httpCode;
        this.code = code;
        this.success=success;
    }
    public ApiResponse() {
    }
}
