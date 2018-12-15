package com.ivyzh.ubing.base;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.utils.GsonUtils;
import com.ivyzh.ubing.domain.request.MyPointer;
import com.ivyzh.ubing.domain.request.MyRelation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ivy on 2018/12/16.
 */

public abstract class BaseSkinActivity extends BaseActivity {


    /**
     * @param className 表名:CommentWorkOrder
     * @param objectId  表主键的值:0ba34ebea1
     */
    protected JSONObject generatePoint(String className, String objectId) {
        MyPointer pointer = new MyPointer(className, objectId);
        String json = GsonUtils.toJson(pointer);
        JSONObject object = null;// 必须要转成JSONObject，否则报错invalid type for key 'author', expected 'Pointer', but got 'String'.
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * @param className 表名:CommentWorkOrder
     * @param objectId  表主键的值:0ba34ebea1
     */
    protected JSONObject generateRelation(String className, String objectId) {
        MyPointer pointer = new MyPointer(className, objectId);
        MyRelation relation = new MyRelation();
        ArrayList<MyPointer> pointers = new ArrayList<>();
        pointers.add(pointer);
        relation.setObjects(pointers);
        String json = GsonUtils.toJson(relation);
        JSONObject object = null;// 必须要转成JSONObject，否则报错invalid type for key 'author', expected 'Pointer', but got 'String'.
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
