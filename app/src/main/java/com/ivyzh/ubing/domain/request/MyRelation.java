package com.ivyzh.ubing.domain.request;

import java.util.List;

/**
 * Created by Ivy on 2018/12/15.
 */

public class MyRelation {

    /**
     * __type : MyPointer
     * className : tableName
     * objectId : objectId
     */
    private String __op = "AddRelation";
    private List<MyPointer> objects;

    public void setObjects(List<MyPointer> objects) {
        this.objects = objects;
    }
}
