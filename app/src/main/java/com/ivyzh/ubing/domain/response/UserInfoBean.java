package com.ivyzh.ubing.domain.response;

/**
 * Created by Ivy on 2018/12/15.
 */

public class UserInfoBean {

    /**
     * createdAt : 2018-12-14 05:13:05
     * gender : 0
     * nickname : ivy
     * objectId : 21d739f9d7
     * portraitUrl : http://img3.duitang.com/uploads/item/201408/19/20140819194253_tia5Y.jpeg
     * updatedAt : 2018-12-15 18:28:56
     * username : zz
     */

    private String createdAt;
    private int gender;
    private String nickname;
    private String objectId;
    private String portraitUrl;
    private String updatedAt;
    private String username;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
