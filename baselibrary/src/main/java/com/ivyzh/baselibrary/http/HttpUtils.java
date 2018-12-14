package com.ivyzh.baselibrary.http;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.ivyzh.baselibrary.http.impl.OkHttpEngine;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    public static Handler mHandler = new Handler();
    // 上下文
    private Context mContext;
    // 网络访问引擎
    private static IHttpEngine mHttpEngine = new OkHttpEngine();
    // 接口地址
    private String mUrl;
    // 请求参数
    private Map<String, Object> mParams;
    private Map<String, Object> mHeaderParams;
    // get请求标识
    private final int GET_REQUEST = 0x0011;
    // post请求标识
    private final int POST_REQUEST = 0x0022;
    // 请求的方式
    private int mRequestMethod = GET_REQUEST;
    // 是否缓存
    private boolean mCache = false;

    private HttpUtils(Context context) {
        this.mContext = context;
        mParams = new HashMap<>();
        mHeaderParams = new HashMap<>();
    }

    // 切换引擎
    public HttpUtils exchangeEngine(IHttpEngine engine) {
        mHttpEngine = engine;
        return this;
    }


    // 可以在Application中配置HttpEngine
    public static void init(IHttpEngine engine) {
        mHttpEngine = engine;
    }


    // 链式调用配置参数--begin
    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    public HttpUtils cache(boolean cache) {
        mCache = cache;
        return this;
    }

    public HttpUtils get() {
        mRequestMethod = GET_REQUEST;
        return this;
    }

    public HttpUtils post() {
        mRequestMethod = POST_REQUEST;
        return this;
    }

    public HttpUtils addParam(String key, Object value) {
        return addParam(key, value, false);
    }

    public HttpUtils addParam(String key, Object value, boolean isURLEncoder) {
        if (isURLEncoder) {//需要编码
            try {
                mParams.put(key, URLEncoder.encode(value.toString(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            mParams.put(key, value);
        }
        return this;
    }


    // 链式调用配置参数--end
    public void execute(HttpCallBack callBack) {

        if (TextUtils.isEmpty(mUrl)) {
            throw new NullPointerException("访问路径不能为空");
        }

        if (callBack == null) {
            callBack = HttpCallBack.DEFAULT_CALL_BACK;
        }

        callBack.onPreExcute(mContext, mHeaderParams, mParams);

        if (mRequestMethod == GET_REQUEST) {
            mHttpEngine.get(mUrl, mHeaderParams, mParams, callBack, mCache);
        } else if (mRequestMethod == POST_REQUEST) {
            mHttpEngine.post(mUrl, mHeaderParams, mParams, callBack, mCache);
        }
    }


}
