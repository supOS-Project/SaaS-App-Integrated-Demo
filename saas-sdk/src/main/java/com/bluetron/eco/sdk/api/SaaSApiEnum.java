package com.bluetron.eco.sdk.api;

import cn.hutool.http.Method;

/**
 * 接口枚举
 * ClassName  ：UserApiConstans
 * Description：
 * Date：2021/3/17 10:18
 * Author：xinwangji@supos.com
 */

public enum SaaSApiEnum {

    // oath2接口
    OAUTH2_GET_AUTHORIZE_CODE("获取授权码","/inter-api/auth/v1/oauth2/authorize",Method.GET),
    OAUTH2_GET_ACCESS_TOKEN("获取AccessToken","/open-api/auth/v2/oauth2/token",Method.POST),
    OAUTH2_REFRESH_TOKEN("授权码模式通过refreshToken获取AccessToken","/open-api/auth/v2/oauth2/token",Method.POST),



    //用户接口
    USER_ADD("用户新增","/open-api/auth/v2/users",Method.POST),
    USER_LISTS("分页获取用户列表","/open-api/auth/v2/users",Method.GET),
    //    USER_BATCH_DELETE("批量删除用户","/open-api/auth/v2/users",Method.DELETE),
    USER_BATCH_DELETE("批量删除用户","/open-api/supos/auth/v2/delete/users",Method.POST),
    USER_DETAIL("用户详情","/open-api/auth/v2/users/%s",Method.GET),
    USER_UPDATE("修改用户","/open-api/auth/v2/users/%s",Method.PUT),
    USER_BIND_ROLE("用户绑定角色","/open-api/auth/v2/users/%s/role",Method.POST),
    USER_UNBIND_ROLE("用户解除绑定角色","/open-api/auth/v2/users/%s/role",Method.PUT),


    //公司
    COMPANY_LIST("公司列表","/open-api/organization/v2/companies",Method.GET),
    COMPANY_DETAIL("公司详情","/open-api/organization/v2/companies/%s",Method.GET),

    //部门
    DEPARTMENTS_LIST("部门列表","/open-api/organization/v2/departments",Method.GET),
    DEPARTMENTS_DETAIL("部门详情","/open-api/organization/v2/departments/%s",Method.GET),

    //岗位
    POSITION_LIST("岗位列表","/open-api/organization/v2/positions",Method.GET),
    POSITION_DETAIL("岗位详情","/open-api/organization/v2/positions/%s",Method.GET),
    POSITION_LIST_BY_COMPANY("查询指定编码的公司的岗位列表","/open-api/organization/v2/companies/%s/positions",Method.GET),

    //人员
    PERSON_LIST("人员列表","/open-api/organization/v2/persons",Method.GET),
    PERSON_DETAIL("人员详情","/open-api/organization/v2/persons/%s",Method.GET),
    PERSON_LIST_BY_COMPANY("查询指定编码的公司的人员列表","/open-api/organization/v2/companies/%s/persons",Method.GET),
    PERSON_LIST_BY_DEPARTMENTS("查询指定编码的部门的人员列表","/open-api/organization/v2/departments/%s/persons",Method.GET),
    PERSON_LIST_BY_POSITION("查询指定编码的岗位的人员列表","/open-api/organization/v2/positions/%s/persons",Method.GET),



    //通知中心
    NOTIFICATION_SEND_MESSAGE("消息发送","/open-api/p/notification/v2/messages",Method.POST),
    NOTIFICATION_SEND_MESSAGE_BY_TOPIC("根据主题发送消息","/open-api/p/notification/v2/topic/messages",Method.POST),

    //工作流
    WORKFLOW_TASKS_PENDING("用户待办列表","/open-api/p/workflow/v2/tasks/pending",Method.GET),
    WORKFLOW_SUBMIT("待办任务提交","/open-api/p/workflow/v2/tasks/pending",Method.POST),
    WORKFLOW_SAVE("保存待办关联的表单数据","/open-api/p/workflow/v2/tasks/pending",Method.PUT),
    WORKFLOW_DETAIL_PENDING("查询待办详情","/open-api/p/workflow/v2/tasks/%s/pending",Method.GET),
    WORKFLOW_TASK_TOTAL("用户待办总数","/open-api/p/workflow/v2/tasks/pending/total",Method.GET),
    WORKFLOW_TASKS_COMPLETE("用户已办列表","/open-api/p/workflow/v2/tasks/completive",Method.GET),
    WORKFLOW_DETAIL_COMPLETE("查询已办详情","/open-api/p/workflow/v2/tasks/%s/completive",Method.GET),
    WORKFLOW_ENTRUST("待办任务委托","/open-api/p/workflow/v2/tasks/entrust",Method.POST),
    WORKFLOW_REVOKE("待办任务撤回","/open-api/p/workflow/v2/tasks/revocation",Method.POST),
    WORKFLOW_START_INFO("查询流程启动详情","/open-api/p/workflow/v2/processes/%s/startInfo",Method.GET),
    WORKFLOW_DETAIL_PROCESS("查询流程详情","/open-api/p/workflow/v2/processes/%s",Method.GET),
    WORKFLOW_PROCESS_LOGS("查询流程日志","/open-api/p/workflow/v2/processes/%s/logs",Method.GET),
    WORKFLOW_URGE_INFO("查询催办信息","/open-api/p/workflow/v2/processes/%s/urgeInfo",Method.GET),
    WORKFLOW_PROCESSES("查询用户发起的流程列表","/open-api/p/workflow/v2/processes",Method.GET),
    WORKFLOW_PROCESS_SAVE("流程启动页保存接口","/open-api/p/workflow/v2/processes",Method.PUT),
    WORKFLOW_PROCESS_START("发起流程","/open-api/p/workflow/v2/processes",Method.POST),
    WORKFLOW_PROCESS_CANCEL("作废流程","/open-api/p/workflow/v2/processes/%s/cancellation",Method.POST),

    //对象建模
    OODM_LABEL_LIST("搜索标签目录列表","/open-api/supos/oodm/v2/model-label-types",Method.GET),
    OODM_LABEL_CATEGORY_CREATE("创建标签类目","/open-api/supos/oodm/v2/model-label-types",Method.POST),
    OODM_LABEL_DETAIL("查询标签/类目","/open-api/supos/oodm/v2/model-label-types/%s/%s",Method.GET),
    OODM_ALL_LABELS("搜索所有标签","/open-api/supos/oodm/v2/model-labels",Method.GET),
    OODM_LABEL_CREATE("创建标签","/open-api/supos/oodm/v2/model-labels",Method.POST),
    OODM_LABEL_INSTANCE_REMOVE("实例解除标签","/open-api/supos/oodm/v2/model-labels/%s/%s/instance-labels",Method.POST),
    OODM_LABEL_INSTANCE_CREATE("实例打标签","/open-api/supos/oodm/v2/model-labels/%s/%s/%s/instance-labels",Method.POST),
    OODM_TEMPLATE_CREATE("创建模板(可同时创建属性报警事件服务订阅)","/open-api/supos/oodm/v2/template",Method.POST),
    OODM_TEMPLATE_LIST_BY_APPNAME("获取app下所有模板","/open-api/supos/oodm/v2/template/listByAppName",Method.GET),
    OODM_TEMPLATE_LIST_BY_LABEL("获取标签下所有模板","/open-api/supos/oodm/v2/template/listByLabel",Method.GET),
    OODM_TEMPLATE_LIST_BY_NAMES("通过别名查询模板","/open-api/supos/oodm/v2/template/listByNames",Method.POST),
    OODM_TEMPLATE_SEARCH("搜索模板","/open-api/supos/oodm/v2/template/search",Method.GET),
    OODM_SEARCH_APPNAMES("搜索所有模板所属的所有app","/open-api/supos/oodm/v2/template/searchAppNamesOfTemplate",Method.GET),
    OODM_TEMPLATE_EDIT("修改模板","/open-api/supos/oodm/v2/template/%s/%s",Method.PUT),
    OODM_TEMPLATE_DELETE("删除模板","/open-api/supos/oodm/v2/template/%s/%s",Method.DELETE),
    OODM_TEMPLATE_CHILDREN("获取所有派生的模板","/open-api/supos/oodm/v2/template/%s/%s/children",Method.GET),
    OODM_TEMPLATE_PARENTS("递归查找所有父模板(结果包含当前模板)","/open-api/supos/oodm/v2/template/%s/%s/parents",Method.GET),
    OODM_TEMPLATE_INSTANCE_CREATE("创建普通模板实例","/open-api/supos/oodm/v2/template/%s/%s/instance",Method.POST),
    OODM_TEMPLATE_INSTANCE_INCREMENT("增量更新实例","/open-api/supos/oodm/v2/templates/%s/%s/instances/create-or-update",Method.POST),
    OODM_TEMPLATE_INSTANCE_QUERY("查询普通模板下实例列表","/open-api/supos/oodm/v2/template/%s/%s/instance/query",Method.POST),
    OODM_TEMPLATE_INSTANCE_EDIT("修改普通模板实例","/open-api/supos/oodm/v2/template/%s/%s/instance/%s",Method.PUT),
    OODM_TEMPLATE_INSTANCE_DELETE("删除实体模板实例","/open-api/supos/oodm/v2/template/%s/%s/instance/%s",Method.DELETE),
    OODM_TEMPLATE_INSTANCE_SEARCH("搜索普通模板下实例列表","/open-api/supos/oodm/v2/template/%s/%s/instances",Method.GET),
    OODM_TEMPLATE_INSTANCE_DETAIL_BY_NAME("根据实例别名获取实例详情","/open-api/supos/oodm/v2/templates/%s/%s/instances/%s/value",Method.GET),
    OODM_TEMPLATE_INSTANCE_DETAIL("获取普通模板下实例详情","/open-api/supos/oodm/v2/template/%s/%s/instances/%s",Method.GET),
    OODM_RE_TEMPLATE_INSTANCES("搜索关系模板下实例列表","/open-api/supos/oodm/v2alpha/templates/%s/%s/relations/%s/%s/instances",Method.GET),
    OODM_RELATIONS("关系搜索接口","/open-api/supos/oodm/v2alpha/relations",Method.GET),
    OODM_RE_TEMPLATE_NODE_DETAIL("获取指定关系模板下关系的节点详情","/open-api/supos/oodm/v2alpha/templates/%s/%s/relations/%s/%s/relationNodes",Method.GET),
    OODM_ATTRIBUTE_UNIT_LIST("获取属性单位列表","/open-api/supos/oodm/v2/attribute/unit/list",Method.GET),
    OODM_ATTRIBUTES("根据查询条件查询所有属性","/open-api/supos/oodm/v2/attributes",Method.GET),
    OODM_ATTRIBUTE_ADD("新增模板属性","/open-api/supos/oodm/v2/template/%s/%s/attribute",Method.POST),
    OODM_ATTRIBUTE_SELF("获取当前模板创建的属性","/open-api/supos/oodm/v2/template/%s/%s/attribute/self",Method.GET),
    OODM_ATTRIBUTE_EDIT("修改模板属性","/open-api/supos/oodm/v2/template/%s/%s/attribute/%s/%s",Method.PUT),
    OODM_ATTRIBUTE_DELETE("删除模板属性","/open-api/supos/oodm/v2/template/%s/%s/attribute/%s/%s",Method.DELETE),
    OODM_ENTITY_INSTANCE_ATTRIBUTE_ADD("新增实体实例扩展属性","/open-api/supos/oodm/v2/template/%s/%s/entity/%s/attribute",Method.POST),
    OODM_ENTITY_INSTANCE_ATTRIBUTE_EDIT("修改实体实例扩展属性","/open-api/supos/oodm/v2/template/%s/%s/entity/%s/attribute/%s/%s",Method.PUT),
    OODM_ENTITY_INSTANCE_ATTRIBUTE_DELETE("删除实体实例扩展属性","/open-api/supos/oodm/v2/template/%s/%s/entity/%s/attribute/%s/%s",Method.DELETE),
    OODM_INSTANCE_ATTRIBUTE_SELF("获取当前实例创建的属性","/open-api/supos/oodm/v2/template/%s/%s/instance/%s/attribute/self",Method.GET),
    OODM_EVENTS("搜索所有事件","/open-api/supos/oodm/v2/events",Method.GET),
    OODM_NETWORKS("查询network列表","/open-api/supos/oodm/v2/networks",Method.GET),
    OODM_NETWORK_CREATE("创建network","/open-api/supos/oodm/v2/networks",Method.POST),
    OODM_NETWORK_EDIT("修改network","/open-api/supos/oodm/v2/networks/%s",Method.PUT),
    OODM_NETWORK_DELETE("删除network","/open-api/supos/oodm/v2/networks/%s",Method.DELETE),
    OODM_NETWORK_NODE_CREATE("创建network节点","/open-api/supos/oodm/v2/networks/%s/nodes",Method.POST),
    OODM_NETWORK_NODES("查询当前节点的子节点树","/open-api/supos/oodm/v2/networks/%s/nodes/%s",Method.GET),
    OODM_NETWORK_NODE_EDIT("修改network节点","/open-api/supos/oodm/v2/networks/%s/nodes/%s",Method.PUT),
    OODM_NETWORK_NODE_DELETE("递归删除network节点","/open-api/supos/oodm/v2/networks/%s/nodes/%s",Method.DELETE),
    OODM_NETWORK_TREE("根据networkName查询network树","/open-api/supos/oodm/v2/networks/%s/tree",Method.GET),
    OODM_ALERT_CURRENT("查询实时报警","/open-api/supos/oodm/v2/alert/current",Method.GET),
    OODM_ATTRIBUTES_CURRENT_SET("批量设置实体实例实时值","/open-api/supos/oodm/v2/attributes/current",Method.PUT),
    OODM_ATTRIBUTE_CURRENT("查询对象属性的实时数据","/open-api/supos/oodm/v2/attribute/current",Method.POST),
    OODM_ATTRIBUTE_HISTORY("查询对象属性的历史数据","/open-api/supos/oodm/v2/attribute/histories",Method.POST),
    OODM_TEMPLATE_INSTANCE_EXECUTE("执行实体实例服务","/open-api/supos/oodm/v2/template/%s/%s/instance/%s/service/%s/%s",Method.POST),
    OODM_TEMPLATE_FORM_EXECUTE("执行表单模板服务","/open-api/supos/oodm/v2/template/%s/%s/service/%s/%s",Method.POST),
    OODM_ALERT_ACK("确认报警信息","/open-api/supos/oodm/v2/alerts/ack",Method.POST),
    OODM_ALARMS_CREATE("批量创建报警","/open-api/supos/oodm/v2/alarms",Method.POST),
    OODM_ALARMS("报警搜索","/open-api/supos/oodm/v2/alarms",Method.GET),
    OODM_ALARM_EDIT("修改报警","/open-api/supos/oodm/v2/alarms/%s",Method.PUT),
    OODM_ALARM_DELETE("删除报警","/open-api/supos/oodm/v2/alarms/%s",Method.DELETE),
    OODM_ALARM_BY_ATTRIBUTE("查询指定属性下的报警","/open-api/supos/oodm/v2/attributes/%s/alarms",Method.GET),
    OODM_ALARM_AUTHORIZED_USERS("查询报警有权限推送的用户列表","/open-api/supos/oodm/v2/alarms/authorized-users",Method.GET),
    OODM_ALARM_HISTORY_DETAIL("查询历史报警详情","/open-api/supos/oodm/v2/alerts/history/%s",Method.GET),
    OODM_ALARM_HISTORY_LIST("查询报警信息的历史记录","/open-api/supos/oodm/v2/alerts/history",Method.GET),

    //APP组态管理
    COMPOSE_PAGES_TREE("查询组态树列表","/open-api/p/compose/v2/pages/tree",Method.GET),
    COMPOSE_PAGE_CREATE("创建页面","/open-api/p/compose/v2/pages",Method.POST),
    COMPOSE_PAGE_DETAIL("查询页面详情","/open-api/p/compose/v2/pages/%s",Method.GET),
    COMPOSE_PAGE_EDIT("修改页面信息","/open-api/p/compose/v2/pages/%s",Method.PUT),
    COMPOSE_PAGE_DELETE("删除页面","/open-api/p/compose/v2/pages/%s",Method.DELETE),
    COMPOSE_LAYOUT_ADD("新增布局","/open-api/p/compose/v2/pages/%s/layouts",Method.POST),
    COMPOSE_LAYOUT_EDIT("修改布局信息","/open-api/p/compose/v2/pages/layouts/%s",Method.PUT),
    COMPOSE_OBJECT_BATCH_QUERY("批量查询对象实例数据","/open-api/p/compose/v2/objectdata/batchQuery",Method.POST),

    //配置信息
    CONFIG_SUPOS_VERSION("查询supOS版本信息","/open-api/supos/config/v2/version",Method.GET),
    //webhook消息
    WEBHOOK_SUBSCRIBE("webhook消息订阅","/open-api/webhook/v2/topics/subscribe", Method.POST),
    WEBHOOK_SUBSCRIBE_READINESS("webhook消息订阅接收就绪","/open-api/webhook/v2/topics/readiness", Method.PUT),
    IGNORE("IGNORE","IGNORE",Method.PUT);

    /**
     * 接口名
     */
    private final String name;

    /**
     * 接口地址
     * 取接口路径的后缀
     * 如：/open-api/auth/v2/oauth2/token
     */
    private String url;

    /**
     * Http Method
     * 如:GET POST DELETE
     */
    private Method method;

    SaaSApiEnum(String name, String url, Method method) {
        this.name = name;
        this.url = url;
        this.method = method;
    }

    public void setUrl(String newUrl){
        this.url = newUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }
}

