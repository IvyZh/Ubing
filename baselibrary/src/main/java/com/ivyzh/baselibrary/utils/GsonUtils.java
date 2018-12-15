package com.ivyzh.baselibrary.utils;

import com.google.gson.Gson;
import com.ivyzh.baselibrary.log.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GsonUtils {
    // 解析一个类上面class信息
    public static Class<?> analysisClazzInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();

        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) types[0];

    }


    // 解析一个类上面class信息
    public static String toJson(Object object) {
        String json = new Gson().toJson(object);
        L.v("GsonUtils toJson -> " + json);
        return json;
    }
}
