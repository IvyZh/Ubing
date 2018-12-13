package com.ivyzh.baselibrary.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GsonUtils {
    // 解析一个类上面class信息
    public static Class<?> analysisClazzInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();

        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) types[0];

    }
}
