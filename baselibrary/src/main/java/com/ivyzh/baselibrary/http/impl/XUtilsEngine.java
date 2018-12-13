package com.ivyzh.baselibrary.http.impl;

import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.IHttpEngine;
import com.ivyzh.baselibrary.log.L;

import java.util.Map;

public class XUtilsEngine implements IHttpEngine {
    @Override
    public void get(String url, Map<String, Object> params, HttpCallBack callBack, boolean cache) {
        L.v("XUtilsEngine get");
    }

    @Override
    public void post(String url, Map<String, Object> params, HttpCallBack callBack, boolean cache) {
        L.v("XUtilsEngine post");

    }
}
