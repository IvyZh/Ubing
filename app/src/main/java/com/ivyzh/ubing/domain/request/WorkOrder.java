package com.ivyzh.ubing.domain.request;

import com.ivyzh.ubing.domain.User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * 工单：需求
 */
public class WorkOrder extends BmobObject {

    private String title;
    private String content;
    private String publishUserId;//对应-->User.objectId
    private User author;


    public WorkOrder(String title, String content, String publishUserId) {
        this.title = title;
        this.content = content;
        this.publishUserId = publishUserId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
