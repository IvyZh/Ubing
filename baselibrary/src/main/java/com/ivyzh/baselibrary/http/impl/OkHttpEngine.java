package com.ivyzh.baselibrary.http.impl;

import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.http.IHttpEngine;
import com.ivyzh.baselibrary.log.L;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpEngine implements IHttpEngine {

    private static OkHttpClient okHttpClient;

    static {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build();
    }

    @Override
    public void get(String url, Map<String, Object> headerParams, Map<String, Object> params, final HttpCallBack callBack, final boolean cache) {
        String joinUrl = joinUrl(url, params);
        L.v("OkHttpEngine get joinUrl -> " + joinUrl);
        Headers headers = builderHeaders(headerParams);
        Request request = new Request.Builder()
                .get()
                .headers(headers)
                .url(joinUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                onFailureAction(callBack, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                onSuccessAction(callBack, result);
            }
        });
    }

    private void onSuccessAction(final HttpCallBack callBack, final String result) {
        HttpUtils.mHandler.post(new Runnable() {
            @Override
            public void run() {
                L.v("onSuccessAction -> " + result);
                if (callBack != null) {
                    callBack.onSuccess(result);
                }
            }
        });
    }

    private void onFailureAction(final HttpCallBack callBack, final IOException e) {
        HttpUtils.mHandler.post(new Runnable() {
            @Override
            public void run() {
                L.v("onFailureAction -> " + e);
                if (callBack != null) {
                    callBack.onError(e);
                }
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> headerParams, Map<String, Object> params, final HttpCallBack callBack, boolean cache) {
        L.v("OkHttpEngine post");
        Headers headers = builderHeaders(headerParams);
        RequestBody requestBody = generateRequestBody(params);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .headers(headers)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                onFailureAction(callBack, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                onSuccessAction(callBack, result);
            }
        });
    }

    private Headers builderHeaders(Map<String, Object> headerParams) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            builder.add(key, value.toString());
        }
        return builder.build();

    }

    private RequestBody generateRequestBody(Map<String, Object> params) {
        JSONObject json = new JSONObject(params);
        RequestBody body = FormBody.create(MediaType.parse("application/json"), json.toString());
        return body;
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
            sb.append(key).append("=").append(value).append("&");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);

    }


}
