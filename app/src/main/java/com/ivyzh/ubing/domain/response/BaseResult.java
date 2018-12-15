package com.ivyzh.ubing.domain.response;

/**
 * Created by Ivy on 2018/12/16.
 * 返回最基本值
 */

public class BaseResult {


    /**
     * createdAt : 2018-12-16 02:23:28
     * objectId : ec77c09ec5
     */

    private String createdAt;
    private String objectId;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
