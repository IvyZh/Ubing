package com.ivyzh.baselibrary.http.impl;

import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.http.IHttpEngine;
import com.ivyzh.baselibrary.log.L;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpEngine implements IHttpEngine {

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack, final boolean cache) {
        L.v("OkHttpEngine get");
        String joinUrl = joinUrl(url, params);
        L.v("joinUrl -> " + joinUrl);
        Request request = new Request.Builder()
                .get()
                .url(joinUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpUtils.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                HttpUtils.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onSuccess(result);
                        }
                    }
                });
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final HttpCallBack callBack, boolean cache) {
        L.v("OkHttpEngine post");
        RequestBody requestBody = generateRequestBody(params);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpUtils.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                HttpUtils.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onSuccess(result);
                        }
                    }
                });
            }
        });
    }

    private RequestBody generateRequestBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            L.v("Key = " + key + ", Value = " + value);
            builder.add(key, value.toString());
        }

        return builder.build();
    }

    private String joinUrl(String url, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if (!url.endsWith("?")) {
            sb.append("?");
        }


        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            L.v("Key = " + key + ", Value = " + value);
            sb.append(key).append("=").append(value).append("&");
        }

        return sb.toString().substring(0, sb.toString().length() - 1);

    }


}
