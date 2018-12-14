package com.ivyzh.ubing.domain;

/**
 * 回复工单
 */
public class FeedBack {
    private String content;//反馈内容
    private String publishUserId;//对应-->User.objectId


    public FeedBack(String content, String publishUserId) {
        this.content = content;
        this.publishUserId = publishUserId;
    }
}
