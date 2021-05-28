package com.bluetron.eco.sdk.dto.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ApiResponseUtil.class);
    public static final String DEFAULT_OK_MSG = "ok";

    public ApiResponseUtil() {
    }
    public static <T> ApiResponse<T> getResponse(String msg, T data, Integer code, Integer httpCode) {
        return new ApiResponse(msg, data, httpCode, code);
    }

    public static <T> ApiResponse<T> getSuccessResponse(String msg, Integer httpCode, T data) {
        return new ApiResponse(msg, data, httpCode, ResultCode.SUCCESS.code(),true);
    }

    public static <T> ApiResponse<T> getFailResponse(String msg, Integer httpCode, T data) {
        return new ApiResponse(msg, data, httpCode, ResultCode.FAIL.code());
    }

    public static <T> ApiResponse<T> getSuccessResponse(T data) {
        return getSuccessResponse("ok", 200, data);
    }

    public static <T> ApiResponse<T> getSuccessResponse(T data, Integer httpCode) {
        return getSuccessResponse("ok", httpCode, data);
    }

    public static <T> ApiResponse<T> getFailResponse(String msg, Integer code, Integer httpCode) {
        return new ApiResponse(msg, (Object)null, httpCode, code,false);
    }

    public static <T> ApiResponse<T> getFailResponse(String msg) {
        return getFailResponse(msg, ResultCode.FAIL.code(), (Integer)null);
    }

    public static <T> ApiResponse<T> getFailResponse(ResultCode resultCode) {
        return getFailResponse(resultCode.message(), resultCode.code(), (Integer)null);
    }

    public static <T> ApiResponse<T> getFailResponse(ResultCode resultCode, Integer httpCode) {
        return getFailResponse(resultCode.message(), resultCode.code(), httpCode);
    }

    public static <T> ApiResponse<T> getSuccessResponse() {
        return getSuccessResponse(null);
    }

    public static <T> ApiResponse<T> responseIfSuccess(HttpResponse response, Class<T> clazz) {
        if (response.isOk()) {
            String resStr = response.body();
            if (StrUtil.isEmpty(resStr)) {
                return getSuccessResponse(null, response.getStatus());
            } else {
                T data = JSON.parseObject(resStr, clazz);
                return getSuccessResponse(data, response.getStatus());
            }
        } else {
            return responseFailed(response, (Exception)null);
        }
    }

    public static <T> ApiResponse<T> responseIfSuccess(HttpResponse response, TypeReference<T> t) {
        if (response.isOk()) {
            T data = JSON.parseObject(response.body(), t, new Feature[0]);
            return getSuccessResponse(data, Objects.isNull(response) ? null : response.getStatus());
        } else {
            return responseFailed(response, (Exception)null);
        }
    }

    public static <T> ApiResponse<T> responseFailed(HttpResponse response, Exception e) {
        if (null == e) {
            JSONObject responseMap = null;

            try {
                String suposErrorData = response.body();
                responseMap = JSONUtil.parseObj(suposErrorData);
                String message = "";
                if (responseMap.containsKey("message")) {
                    message = (String)responseMap.get("message");
                }

                return getFailResponse(message, ResultCode.INTERFACE_INNER_INVOKE_ERROR.code(), Objects.isNull(response) ? null : response.getStatus());
            } catch (Exception var5) {
                return getFailResponse(response.body(), ResultCode.INTERFACE_INNER_INVOKE_ERROR.code(), Objects.isNull(response) ? null : response.getStatus());
            }
        } else {
            log.error("get response error:", e);
            return getFailResponse(e.getMessage(), ResultCode.INTERFACE_INNER_INVOKE_ERROR.code(), Objects.isNull(response) ? null : response.getStatus());
        }
    }
}
