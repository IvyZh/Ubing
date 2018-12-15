package com.ivyzh.baselibrary.http.impl;

import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.IHttpEngine;
import com.ivyzh.baselibrary.log.L;

import java.util.Map;

// no use
public abstract class XUtilsEngine implements IHttpEngine {

    @Override
    public void get(String url, Map<String, Object> headerParams, Map<String, Object> params, HttpCallBack callBack, boolean cache) {

    }

    @Override
    public void post(String url, Map<String, Object> headerParams, Map<String, Object> params, HttpCallBack callBack, boolean cache) {

    }
}
