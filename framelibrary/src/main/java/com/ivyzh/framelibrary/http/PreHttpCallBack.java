package com.ivyzh.framelibrary.http;

import android.content.Context;

import com.google.gson.Gson;
import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.utils.GsonUtils;

import java.util.Map;

public abstract class PreHttpCallBack<T> implements HttpCallBack {
    @Override
    public void onPreExcute(Context context, Map headerParams, Map params) {

        headerParams.put("X-Bmob-Application-Id", KeyManager.BMOB_APP_KEY);
        headerParams.put("X-Bmob-REST-API-Key", KeyManager.BMOB_REST_API_Key);
        headerParams.put("Content-Type", "application/json");

       /* params.put("app_name", "ubing");
        params.put("version", "1.0");
        params.put("brand", "huawei");
        params.put("model", "x8");
        params.put("os", "android");*/
    }


    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        T resultJson = (T) gson.fromJson(result, GsonUtils.analysisClazzInfo(this));
        onSuccess(resultJson);
    }

    public abstract void onSuccess(T resultJson);
}
