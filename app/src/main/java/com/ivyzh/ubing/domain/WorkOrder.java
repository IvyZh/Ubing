package com.ivyzh.ubing.domain;

import cn.bmob.v3.BmobObject;

public class WorkOrder extends BmobObject {

    private String title;
    private String content;
    private int publishUserId;//对应-->User.objectId
}
