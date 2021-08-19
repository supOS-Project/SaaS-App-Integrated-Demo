package com.dev.happy.tenant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluetron.eco.sdk.dto.ActionType;
import com.bluetron.eco.sdk.dto.TopicType;
import com.bluetron.eco.sdk.dto.common.ResponseResult;
import com.dev.happy.tenant.constant.AuthConstant;
import com.dev.happy.tenant.entity.MessageHistory;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.service.*;
import com.dev.happy.tenant.utils.CookieUtil;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import com.dev.happy.tenant.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
    @Resource
    private UserService userService;
    @Resource
    private PersonService personService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private CompanyService companyService;
    @Resource
    MessageHistoryService messageHistoryService;
    @Resource
    private SyncService syncService;
    @Resource
    private RedisStandaloneUtils redisStandaloneUtils;

    @PostMapping("/callback/{tenantId}")
    @ResponseBody
    public ResponseResult callbackForTenant(@PathVariable String tenantId, @RequestBody String message) {
        log.info("tenantId:{},message:{}", tenantId, message);
        MessageHistory messageHistory = new MessageHistory();
        System.out.println(message);
        JSONObject payload = JSONObject.parseObject(message).getJSONObject("payload");
        ActionType actionType = payload.getObject("header", ActionType.class);
        String topicType = payload.getString("topic");
        JSONArray body = payload.getJSONArray("body");
        messageHistory.setType(topicType);
        messageHistory.setAction(actionType.getEvent());
        messageHistory.setContext(message);
        messageHistory.setTenantId(tenantId);
        messageHistory.setReceiveTime(new Date());
        messageHistoryService.save(messageHistory);
        switch (topicType) {
            case TopicType.TOPIC_COMPANY:
                companyService.messageHandle(tenantId, actionType, body);
                break;
            case TopicType.TOPIC_DEPARTMENT:
                departmentService.messageHandle(tenantId, actionType, body);
                break;
            case TopicType.TOPIC_PERSON:
                personService.messageHandle(tenantId, actionType, body);
                break;
            case TopicType.TOPIC_USER:
                userService.messageHandle(tenantId, actionType, body);
        }
        return ResponseResult.SUCCESS();
    }

    @GetMapping
    public String view() {
        return "manage/message.html";
    }

    @GetMapping("list")
    @ResponseBody
    public PageResult<List<MessageHistory>> list(Long page, Long limit, HttpServletRequest request) {
        String tenantId = CookieUtil.getCookie("tenantId", request);
        LambdaQueryWrapper<MessageHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageHistory::getTenantId, tenantId).orderByDesc(MessageHistory::getReceiveTime);
        IPage<MessageHistory> iPage = new Page<>(page, limit);
        messageHistoryService.page(iPage, queryWrapper);
        PageResult<List<MessageHistory>> list = new PageResult<>();
        list.setCount(iPage.getTotal());
        list.setData(iPage.getRecords());
        return list;
    }

    @GetMapping("/subscribe")
    @ResponseBody
    public ResponseResult subscribe(HttpServletRequest request) {
        String tenantId = CookieUtil.getCookie("tenantId", request);
        String token = CookieUtil.getCookie("userToken", request);
        String accessTokenKey = token.split("-")[0];
        String accessToken = (String)redisStandaloneUtils.get(AuthConstant.ACCESS_TOKEN+":"+accessTokenKey);
        Tenant tenant = JSONObject.parseObject((String)redisStandaloneUtils.get("TENANT-" + tenantId), Tenant.class);
        syncService.subscribeTopic(tenant, accessToken);
        return ResponseResult.SUCCESS();
    }

    @GetMapping("/readiness")
    @ResponseBody
    public ResponseResult readiness(HttpServletRequest request) {
        String tenantId = CookieUtil.getCookie("tenantId", request);
        String token = CookieUtil.getCookie("userToken", request);
        String accessTokenKey = token.split("-")[0];
        String accessToken =(String) redisStandaloneUtils.get(AuthConstant.ACCESS_TOKEN+":"+accessTokenKey);
        Tenant tenant = JSONObject.parseObject((String)redisStandaloneUtils.get("TENANT-" + tenantId), Tenant.class);
        syncService.receiveReadiness(tenant, accessToken);
        return ResponseResult.SUCCESS();
    }
}