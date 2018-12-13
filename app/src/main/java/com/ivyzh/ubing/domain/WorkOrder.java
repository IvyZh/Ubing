package com.ivyzh.ubing.domain;

import cn.bmob.v3.BmobObject;

/**
 * 工单：需求
 */
public class WorkOrder extends BmobObject {

    private String title;
    private String content;
    private String publishUserId;//对应-->User.objectId


    public WorkOrder(String title, String content, String publishUserId) {
        this.title = title;
        this.content = content;
        this.publishUserId = publishUserId;
    }
}
