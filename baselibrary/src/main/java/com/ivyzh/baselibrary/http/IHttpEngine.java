package com.ivyzh.baselibrary.http;

import java.util.Map;

/**
 * 基本的网络引擎
 */
public interface IHttpEngine {




    void get(String url, Map<String, Object> params, HttpCallBack callBack, boolean cache);

    void post(String url, Map<String, Object> params, HttpCallBack callBack, boolean cache);

    // 取消请求
    // 下载文件
    // 上传文件
    // https添加安全证书
}
