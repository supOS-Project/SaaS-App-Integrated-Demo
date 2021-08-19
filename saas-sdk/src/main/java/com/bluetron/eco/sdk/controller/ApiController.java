package com.bluetron.eco.sdk.controller;


import cn.hutool.json.JSONUtil;
import com.bluetron.eco.sdk.config.SuposConfig;
import com.bluetron.eco.sdk.dto.common.ResponseResult;
import com.bluetron.eco.sdk.dto.common.ResultCode;
import com.bluetron.eco.sdk.dto.tenant.OpeningAppTenantReqDTO;
import com.bluetron.eco.sdk.dto.tenant.QuerySaasAppReqDTO;
import com.bluetron.eco.sdk.dto.tenant.RenewAppTenantReqDTO;
import com.bluetron.eco.sdk.util.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author cnq
 */

public abstract class ApiController {
    public static final String PUBLIC_KEY = SuposConfig.getRsaPublicKey();
    private final Logger log = LoggerFactory.getLogger(ApiController.class);

    /**
     * 租户开通
     * 开放平台会在开发者提交新应用时校验接口的可达性，请求不带参数所以需加required = false 确保接口可调用不报异常
     *
     * @param param * @throws Exception
     * @return
     */
    @PostMapping("/tenants")
    public ResponseResult openTenant(@RequestBody(required = false) OpeningAppTenantReqDTO param) throws Exception {
        if (param == null) {
            return ResponseResult.FAIL(ResultCode.INVALID_INPUT);
        }
        log.info("租户开通==》参数:{}", JSONUtil.toJsonStr(param));
        // 校验参数签名，签名校验不通过则请求不合法
        boolean verify = RSAUtil.verify(param.createSignString(), PUBLIC_KEY, param.getSign());
        if (!verify) {
            return ResponseResult.FAIL(ResultCode.INVALID_CREDENTIAL);
        }
        return doOpenTenant(param);
    }

    /**
     * SaaS Server端 租户开通的具体逻辑
     *
     * @param param
     * @return
     */
    public abstract ResponseResult doOpenTenant(OpeningAppTenantReqDTO param);

    /**
     * 租户状态查询
     *
     * @param param * @throws Exception
     * @return
     */
    @PostMapping("/tenants/status")
    public ResponseResult changeStatus(@RequestBody QuerySaasAppReqDTO param) throws Exception {
        log.info("租户状态查询==》参数:{}", JSONUtil.toJsonStr(param));
        // 校验参数签名，签名校验不通过则请求不合法
        boolean verify = RSAUtil.verify(param.createSignString(), PUBLIC_KEY, param.getSign());
        if (!verify) {
            return ResponseResult.FAIL(ResultCode.INVALID_CREDENTIAL);
        }
        return doQueryStatus(param);
    }

    /**
     * 租户状态查询的具体实现
     *
     * @param param
     * @return
     */
    public abstract ResponseResult doQueryStatus(QuerySaasAppReqDTO param);

    /**
     * 租户续期
     *
     * @param param * @throws Exception
     * @return
     */
    @PostMapping("/renew")
    public ResponseResult renew(@RequestBody RenewAppTenantReqDTO param) throws Exception {
        log.info("租户续期==》参数:{}", JSONUtil.toJsonStr(param));
        // 校验参数签名，签名校验不通过则请求不合法
        boolean verify = RSAUtil.verify(param.createSignString(), PUBLIC_KEY, param.getSign());
        if (!verify) {
            return ResponseResult.FAIL(ResultCode.INVALID_CREDENTIAL);
        }
        return doRenew(param);

    }

    /**
     * 租户续期的具体实现
     *
     * @param param
     * @return
     */
    public abstract ResponseResult doRenew(RenewAppTenantReqDTO param);

    /**
     * 租户停止
     *
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/stop")
    public ResponseResult stop(@RequestBody QuerySaasAppReqDTO param) throws Exception {
        log.info("租户停止==》参数:{}", JSONUtil.toJsonStr(param));
        // 校验参数签名，签名校验不通过则请求不合法
        boolean verify = RSAUtil.verify(param.createSignString(), PUBLIC_KEY, param.getSign());
        if (!verify) {
            return ResponseResult.FAIL(ResultCode.INVALID_CREDENTIAL);
        }
        return doStop(param);
    }

    /**
     * 租户停止的具体实现
     *
     * @param param
     * @return
     */
    public abstract ResponseResult doStop(QuerySaasAppReqDTO param);
}

