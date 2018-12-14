package com.ivyzh.ubing.domain;

public class BaseModel {


    /**
     * createdAt : 2018-12-14 14:12:28
     * objectId : 68f3da2909
     * sessionToken : 7d451d4a40ae600680a26c13ff502e73
     * code : 202
     * error : username 'zz2' already taken.
     */

    private String createdAt;
    private String objectId;
    private String sessionToken;
    private int code;
    private String error;

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

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
