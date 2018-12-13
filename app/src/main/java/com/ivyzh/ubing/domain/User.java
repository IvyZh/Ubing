package com.ivyzh.ubing.domain;


import cn.bmob.v3.BmobUser;

/**
 * 用户基类BmobUser，拥有注册、登录、修改密码、重置密码、短信操作、邮箱操作、第三方操作等功能。
 * username	用户名/账号/用户唯一标志，可以是邮箱、手机号码、第三方平台的用户唯一标志
 * password	用户密码
 * email	用户邮箱
 * emailVerified	用户邮箱认证状态
 * mobilePhoneNumber	用户手机号码
 * mobilePhoneNumberVerified	用户手机号码认证状态
 */
public class User extends BmobUser {
    private String nickname;
    private Integer gender;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getObjectId() + '\'' +
                "nickname='" + nickname + '\'' +
                ", gender=" + gender +
                '}';
    }
}
