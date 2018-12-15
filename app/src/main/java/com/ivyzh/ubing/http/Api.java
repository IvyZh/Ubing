package com.ivyzh.ubing.http;

/**
 * Created by Ivy on 2018/12/14.
 */

public class Api {
    private static final String HOST_URL = "https://api.bmob.cn/1";
    public static final String REGISTER = HOST_URL + "/users";//注册用户
    public static final String LOGIN = HOST_URL + "/login/";//登录用户
    public static final String CURRENT_USER_INFO = HOST_URL + "/users/{objectID}";//获取当前用户
    public static final String FEED_BACK = HOST_URL + "/classes/FeedBack";
    public static final String WORK_ORDER = HOST_URL + "/classes/WorkOrder";
    public static final String WORK_ORDER_INFO = HOST_URL + "/classes/WorkOrder/{id}";
    public static final String WORK_ORDER_COMMENT = HOST_URL + "/classes/CommentWorkOrder";

}
