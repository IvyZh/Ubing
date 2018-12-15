package com.ivyzh.ubing.domain.request;

/**
 * Created by Ivy on 2018/12/15.
 */

public class MyPointer {

    /**
     * __type : MyPointer
     * className : tableName
     * objectId : objectId
     */

    private String __type = "Pointer";
    private String className;
    private String objectId;

    public MyPointer(String className, String objectId) {
        this.className = className;
        this.objectId = objectId;
    }


}
