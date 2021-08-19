package com.bluetron.eco.sdk.dto;

/**
 * webhook可订阅基础信息类型
 *
 * @author caonuoqi@supos.com
 */
public class TopicType {
    /**
     * webhook-公司事件
     */
    public final static String TOPIC_COMPANY = "supOS_company_event";
    /**
     * webhook-部门事件
     */
    public final static String TOPIC_DEPARTMENT = "supOS_department_event";
    /**
     * webhook-岗位事件
     */
    public final static String TOPIC_POSITION = "supOS_position_event";
    /**
     * webhook-人员事件
     */
    public final static String TOPIC_PERSON = "supOS_person_event";
    /**
     * webhook-用户事件
     */
    public final static String TOPIC_USER = "supOS_user_event";
}
