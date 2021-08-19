package com.bluetron.eco.sdk.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.*;
import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.config.SuposConfig;
import com.bluetron.eco.sdk.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author caonuoqi@supos.com
 */
public class SaaSApi {
    private static final Logger log = LoggerFactory.getLogger(SaaSApi.class);
    public static SaaSAuthService authService = new SaaSAuthService();
    private static final String BOSS_PATH = SuposConfig.getBossBaseUrl() + "/ess-gate/%s/%s";

    public static HttpResponse doRequest(String instanceName, String region, String accessToken, Object requestBody, SaaSApiEnum SaaSApiEnum, Object... pathParam) {
        long t1 = System.currentTimeMillis();
        String url = SaaSApiEnum.getUrl();
        Method method = SaaSApiEnum.getMethod();
        if (null != pathParam && pathParam.length > 0) {
            url = String.format(SaaSApiEnum.getUrl(), pathParam);
        }
        String bossApi = String.format(BOSS_PATH, region, instanceName);
        String wholeUrl = bossApi.concat(url);
        log.info(">>>>>>>>>>>>> 开始调用openAPI request URL : {} , method : {} , accessToken : {} , body : {} <<<<<<<<<<<<<<<<", new Object[]{wholeUrl, SaaSApiEnum.getMethod(), accessToken, JSONUtil.toJsonStr(requestBody)});
        HttpRequest httpRequest = HttpUtil.createRequest(method, wholeUrl);
        Map<String, String> headers = getSignatureHeader(requestBody, url, method, accessToken, instanceName, region);
        httpRequest.headerMap(headers, false);
        switch (SaaSApiEnum.getMethod()) {
            case GET:
            case DELETE:
                Map<String, Object> queryMap = BeanUtil.beanToMap(requestBody);
                httpRequest.form(queryMap);
                break;
            case POST:
            case PUT:
            case PATCH:
                httpRequest.body(JSONUtil.toJsonStr(requestBody));
        }

        HttpResponse response = httpRequest.execute();
        log.info(">>>>>>>>>>>>> 调用openAPI结束 HttpCode : {} , 返回结果 : {} , 耗时：{}ms", new Object[]{response.getStatus(), response.body(), System.currentTimeMillis() - t1});
        return response;
    }

    public static HttpResponse doRequest(String instanceName, String region, String accessToken, SaaSApiEnum SaaSApiEnum, Object... pathParam) {
        return doRequest(instanceName, region, accessToken, null, SaaSApiEnum, pathParam);
    }

    private static Map<String, String> getSignatureHeader(Object requestBody, String url, Method method, String accessToken, String instanceName, String region) {
        Map<String, String> headers = MapUtil.newHashMap();
        headers.put("X-MC-Date", DateUtil.format(new Date(), "yyyyMMdd'T'HHmmss'Z'"));
        headers.put("X-MC-Type", "openAPI");
        headers.put("token", accessToken);
        StringBuilder sb = new StringBuilder();
        //HTTP Schema
        sb.append(method).append("\n");
        //HTTP URI
        String uri = String.format(BOSS_PATH, region, instanceName).replace(SuposConfig.getBossBaseUrl(), "").concat(url);
        sb.append(uri).append("\n");
        //HTTP ContentType
        sb.append("application/json;charset=utf-8").append("\n");
        String signature;
        if (Method.GET.equals(method)) {
            Map<String, Object> queryMap = BeanUtil.beanToMap(requestBody);
            signature = beanToQueryString(queryMap, false);
            signature = getSortQueryStr(signature);
            sb.append(signature);
        }
        sb.append("\n");
        sb.append(getCanonicalCustomHeaders(headers));
        log.info(">>>>>>>>>>>>> AK/SK APPKEY : {}  SECRET_KEY : {} <<<<<<<<<<<<<<<<", SuposConfig.getBossAppId(), SuposConfig.getBossSecretKey());
        log.info(">>>>>>>>>>>>> AK/SK 签名源内容：\n{} \n<<<<<<<<<<<<<<<<", sb);
        signature = DigestUtil.hmac(HmacAlgorithm.HmacSHA256, SuposConfig.getBossSecretKey().getBytes(StandardCharsets.UTF_8)).digestHex(sb.toString());
        String finalSignature = "Sign " + SuposConfig.getBossAppId() + "-" + signature;
        headers.put("Authorization", finalSignature);
        headers.put(Header.CONTENT_TYPE.getValue(), "application/json;charset=utf-8");
        log.info(">>>>>>>>>>>>> AK/SK 签名结果：{} <<<<<<<<<<<<<<<<", finalSignature);
        return headers;
    }

    private static String getCanonicalCustomHeaders(Map<String, String> headMap) {
        return "x-mc-date:" + headMap.get("X-MC-Date") + ";x-mc-type:" + headMap.get("X-MC-Type");
    }

    private static String getSortQueryStr(String apiPath) {
        if (StrUtil.isBlank(apiPath)) {
            return "";
        } else {
            Map<String, Object> map = getUrlParams(apiPath);
            TreeMap<String, Object> queryKvMap = new TreeMap();
            if (MapUtil.isEmpty(map)) {
                return "";
            } else {
                Iterator iterator = map.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry) iterator.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    queryKvMap.put(key.toLowerCase(Locale.ROOT), value);
                }

                return getUrlParamsByMap(queryKvMap);
            }
        }
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator var2 = map.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var2.next();
                sb.append((String) entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }

            String s = sb.toString();
            if (s.endsWith("&")) {
                s = StrUtil.subBefore(s, "&", true);
            }

            return s;
        }
    }

    private static Map<String, Object> getUrlParams(String url) {
        Map<String, Object> map = new HashMap(0);
        String[] params = url.split("&");
        String[] var3 = params;
        int var4 = params.length;
        for (int var5 = 0; var5 < var4; ++var5) {
            String param = var3[var5];
            String[] p = param.split("=");
            if (p.length == 2) {
                if (!"null".equals(p[1])) {
                    map.put(p[0], p[1]);
                }
            } else {
                map.put(p[0], "");
            }
        }
        return map;
    }

    private static String beanToQueryString(Map<String, Object> paramMap, boolean isEncoder) {
        if (isEncoder) {
            return HttpUtil.toParams(paramMap);
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            paramMap.forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }
}