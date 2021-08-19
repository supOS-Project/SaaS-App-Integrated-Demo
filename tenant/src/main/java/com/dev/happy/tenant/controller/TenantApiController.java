package com.dev.happy.tenant.controller;

import cn.hutool.core.util.ObjectUtil;
import com.bluetron.eco.sdk.controller.ApiController;
import com.bluetron.eco.sdk.dto.common.ResponseResult;
import com.bluetron.eco.sdk.dto.common.ResultCode;
import com.bluetron.eco.sdk.dto.tenant.*;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cnq
 */
@RestController
@RequestMapping("/open-api/app")
@Slf4j
public class TenantApiController extends ApiController {
    @Resource
    private TenantService tenantService;

    /**
     * 租户开通
     * 开放平台会在开发者提交新应用时校验接口的可达性，请求不带参数所以需加required = false 确保接口可调用不报异常
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult doOpenTenant(OpeningAppTenantReqDTO param){
        //检查租户是否已经开通
        Tenant tenantChecked = tenantService.getTenant(param.getInstanceId(), param.getAppId(), null);
        if (!ObjectUtil.isNull(tenantChecked)) {
            return ResponseResult.FAIL(ResultCode.TENANT_EXIST);
        }
        //开通租户
        OpeningAppTenantResDTO res = tenantService.openTenant(param);
        return ResponseResult.SUCCESS(res);
    }

    /**
     * 租户状态查询
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult doQueryStatus(QuerySaasAppReqDTO param){
        Tenant tenantChecked = tenantService.getTenant(null, param.getAppId(), param.getTenantId());
        if (ObjectUtil.isNull(tenantChecked)) {
            return ResponseResult.FAIL(ResultCode.TENANT_NOT_EXIST);
        }
        QuerySaasAppResDTO res = new QuerySaasAppResDTO();
        res.setTenantId(tenantChecked.getTenantId());
        //有菜时 不需要设置此字段，有的APP没有菜单  所以是baseUrl?tenantId=***   作为主页的
       // res.setTenantUrl("http://47.107.149.177:30001/home");
        return ResponseResult.SUCCESS(res);
    }

    /**
     * 租户续期
     *
     * @param param
     * @return
     */
   @Override
    public ResponseResult doRenew(RenewAppTenantReqDTO param) {
        Tenant tenantChecked = tenantService.getTenant(null, param.getAppId(), param.getTenantId());
        if (ObjectUtil.isNull(tenantChecked)) {
            return ResponseResult.FAIL(ResultCode.TENANT_NOT_EXIST);
        }
        tenantService.renew(param, tenantChecked);
        return ResponseResult.SUCCESS();
    }

    /**
     * 租户停止
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult doStop( QuerySaasAppReqDTO param) {
        Tenant tenantChecked = tenantService.getTenant(null, param.getAppId(), param.getTenantId());
        if (ObjectUtil.isNull(tenantChecked)) {
            return ResponseResult.FAIL(ResultCode.TENANT_NOT_EXIST);
        }
        tenantService.stop(tenantChecked);
        return ResponseResult.SUCCESS();
    }

}
