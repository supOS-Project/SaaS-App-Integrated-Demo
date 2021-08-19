package com.dev.happy.tenant.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.api.SaaSApiEnum;
import com.bluetron.eco.sdk.dto.TopicType;
import com.dev.happy.tenant.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class SyncService {
    @Value("${app.webhook-callback}")
    private String webhookCallback;
    @Resource
    private SyncLogService syncLogService;
    @Resource
    private CompanyService companyService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PersonService personService;

    @Resource
    private UserService userService;
    private static final String TYPE_COMPANY = "sync_company_list";
    private static final String TYPE_DEPARTMENT = "sync_department_list";
    private static final String TYPE_PERSON = "sync_person_list";
    private static final String TYPE_USER = "sync_user_list";
    /**
     * 订阅的基础信息 消息列表
     */
    private List<String> subscribeList = Arrays.asList(
            TopicType.TOPIC_COMPANY,
            TopicType.TOPIC_DEPARTMENT,
            TopicType.TOPIC_POSITION,
            TopicType.TOPIC_PERSON,
            TopicType.TOPIC_USER);
    public void subscribeTopic(Tenant tenant, String accessToken) {
        JSONObject param = new JSONObject();
        param.put("appId", SecureUtil.md5(tenant.getAppId() + ":" + tenant.getTenantId()));
        param.put("tenantId", tenant.getInstanceName().replace("ess-",""));
        param.put("webhookUrl", webhookCallback + tenant.getTenantId());
        param.put("topicList", subscribeList);
        SaaSApi.doRequest(tenant.getInstanceName(),tenant.getRegion(), accessToken, param, SaaSApiEnum.WEBHOOK_SUBSCRIBE, null);
    }

    public void syncAll(Tenant tenant, String accessToken) {
        LambdaQueryWrapper<SyncLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SyncLog::getTenantId, tenant.getTenantId());
        int count = syncLogService.count(queryWrapper);
        if (count == 0) {
            subscribeTopic(tenant, accessToken);
            companySync(tenant, accessToken);
            departmentSync(tenant, accessToken);
            personSync(tenant, accessToken);
            userSync(tenant, accessToken);
            receiveReadiness(tenant, accessToken);
        }
    }

    public void receiveReadiness(Tenant tenant, String accessToken) {
        JSONObject param = new JSONObject();
        param.put("appId", SecureUtil.md5(tenant.getAppId() + ":" + tenant.getTenantId()));
        param.put("tenantId", tenant.getInstanceName().replace("ess-",""));
        param.put("topicList", subscribeList);
        SaaSApi.doRequest(tenant.getInstanceName(),tenant.getRegion(), accessToken, param, SaaSApiEnum.WEBHOOK_SUBSCRIBE_READINESS, null);
    }

    /**
     * 公司信息全量同步
     */
    private void companySync(Tenant tenant, String accessToken) {
        boolean companySync = syncLogService.checkExistByType(tenant.getTenantId(), TYPE_COMPANY);
        if (!companySync) {
            JSONObject reqBody = new JSONObject();
            reqBody.put("current", 1);
            reqBody.put("pageSize", 500);
            HttpResponse companyResponse = SaaSApi.doRequest(tenant.getInstanceName(), tenant.getRegion(), accessToken, reqBody, SaaSApiEnum.COMPANY_LIST, null);
            JSONObject companyResult = JSONObject.parseObject(companyResponse.body());
            if (companyResult.getJSONObject("pagination").getInteger("total") > 0) {
                List<Company> companyList = JSONObject.parseArray(companyResult.getString("list"), Company.class);
                companyList.forEach(x -> {
                    x.setTenantId(tenant.getTenantId());
                    LambdaQueryWrapper<Company> queryWrapper=new LambdaQueryWrapper<>();
                    queryWrapper.eq(Company::getTenantId,tenant.getTenantId()).eq(Company::getCode,x.getCode());
                    companyService.saveOrUpdate(x,queryWrapper);
                });
                syncLogService.saveLogByType(tenant.getTenantId(), TYPE_COMPANY);
            }
        }
    }

    /**
     * 部门信息全量同步
     */
    private void departmentSync(Tenant tenant, String accessToken) {
        boolean departmentSync = syncLogService.checkExistByType(tenant.getTenantId(), TYPE_DEPARTMENT);
        if (!departmentSync) {
            JSONObject reqBody = new JSONObject();
            reqBody.put("current", 1);
            reqBody.put("pageSize", 500);
            HttpResponse departmentResponse = SaaSApi.doRequest(tenant.getInstanceName(), tenant.getRegion(), accessToken, reqBody, SaaSApiEnum.DEPARTMENTS_LIST, null);
            JSONObject departmentResult = JSONObject.parseObject(departmentResponse.body());
            if (departmentResult.getJSONObject("pagination").getInteger("total") > 0) {
                List<Department> departmentList = JSONObject.parseArray(departmentResult.getString("list"), Department.class);
                departmentList.forEach(x -> {
                    x.setTenantId(tenant.getTenantId());
                    LambdaQueryWrapper<Department> queryWrapper=new LambdaQueryWrapper<>();
                    queryWrapper.eq(Department::getTenantId,tenant.getTenantId()).eq(Department::getCode,x.getCode());
                    departmentService.saveOrUpdate(x,queryWrapper);
                });
                syncLogService.saveLogByType(tenant.getTenantId(), TYPE_DEPARTMENT);
            }
        }
    }

    /**
     * 人员信息全量同步
     */
    private void personSync(Tenant tenant, String accessToken) {
        boolean departmentSync = syncLogService.checkExistByType(tenant.getTenantId(), TYPE_PERSON);
        if (!departmentSync) {
            JSONObject reqBody = new JSONObject();
            reqBody.put("current", 1);
            reqBody.put("pageSize", 100);
            HttpResponse personResponse = SaaSApi.doRequest(tenant.getInstanceName(), tenant.getRegion(), accessToken, reqBody, SaaSApiEnum.PERSON_LIST, null);
            JSONObject personResult = JSONObject.parseObject(personResponse.body());
            if (personResult.getJSONObject("pagination").getInteger("total") > 0) {
                List<Person> personList = JSONObject.parseArray(personResult.getString("list"), Person.class);
                personList.forEach(x -> {
                    x.setTenantId(tenant.getTenantId());
                    LambdaQueryWrapper<Person> queryWrapper=new LambdaQueryWrapper<>();
                    queryWrapper.eq(Person::getTenantId,tenant.getTenantId()).eq(Person::getCode,x.getCode());
                    personService.saveOrUpdate(x,queryWrapper);
                });
                syncLogService.saveLogByType(tenant.getTenantId(), TYPE_PERSON);
            }
        }
    }

    /**
     * 用户信息全量同步
     */
    private void userSync(Tenant tenant, String accessToken) {
        boolean departmentSync = syncLogService.checkExistByType(tenant.getTenantId(), TYPE_USER);
        if (!departmentSync) {
            List<String> companyList = companyService.companyCodeList(tenant.getTenantId());
            JSONObject reqBody = new JSONObject();
            reqBody.put("pageIndex", 1);
            reqBody.put("pageSize", 500);
            for (int i = 0; i < companyList.size(); i++) {
                reqBody.put("companyCode", companyList.get(i));
                HttpResponse userResponse = SaaSApi.doRequest(tenant.getInstanceName(), tenant.getRegion(), accessToken, reqBody, SaaSApiEnum.USER_LISTS, null);
                JSONObject userResult = JSONObject.parseObject(userResponse.body());
                if (userResult.getJSONObject("pagination").getInteger("total") > 0) {
                    JSONArray jsonArray = userResult.getJSONArray("list");
                    for (int j = 0; j < jsonArray.size(); j++) {
                        JSONObject userJson = jsonArray.getJSONObject(j);
                        User user = userJson.toJavaObject(User.class);
                        user.setName(userJson.getString("username"));
                        user.setTenantId(tenant.getTenantId());
                        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
                        queryWrapper.eq(User::getTenantId,tenant.getTenantId()).eq(User::getName,user.getName());
                        userService.saveOrUpdate(user,queryWrapper);
                    }
                }
            }
            syncLogService.saveLogByType(tenant.getTenantId(), TYPE_USER);
        }
    }
}
