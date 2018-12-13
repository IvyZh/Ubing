package com.ivyzh.ubing.domain;

import cn.bmob.v3.BmobObject;

/**
 * 回复工单
 */
public class CommentWorkOrder extends BmobObject {

    private String workOrderId;//对应-->WorkOrder.objectId
    private String content;//回复内容
    private String publishUserId;//对应-->User.objectId

    public CommentWorkOrder(String workOrderId, String content, String publishUserId) {
        this.workOrderId = workOrderId;
        this.content = content;
        this.publishUserId = publishUserId;
    }
}
