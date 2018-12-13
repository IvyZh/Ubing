package com.ivyzh.baselibrary.http;

import android.content.Context;

import java.util.Map;

public interface HttpCallBack {


    void onPreExcute(Context context, Map<String, Object> params);

    void onError(Exception e);

    void onSuccess(String result);


    HttpCallBack DEFAULT_CALL_BACK = new HttpCallBack() {
        @Override
        public void onPreExcute(Context context, Map params) {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {
        }
    };
}
