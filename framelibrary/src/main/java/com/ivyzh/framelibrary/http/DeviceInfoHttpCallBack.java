package com.ivyzh.framelibrary.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.gson.Gson;
import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.utils.GsonUtils;

import java.util.Map;

public abstract class DeviceInfoHttpCallBack<T> implements HttpCallBack {
    @Override
    public void onPreExcute(Context context, Map headerParams, Map params) {
        headerParams.put("X-Bmob-Application-Id", KeyManager.BMOB_APP_KEY);
        headerParams.put("X-Bmob-REST-API-Key", KeyManager.BMOB_REST_API_Key);
        params.put("versionCode", getVersionCode(context));
        params.put("versionName", getVersionName(context));
        params.put("brand", android.os.Build.BRAND);
        params.put("model", Build.MODEL);
        params.put("os", "android");

    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        T resultJson = (T) gson.fromJson(result, GsonUtils.analysisClazzInfo(this));
        onSuccess(resultJson);
    }

    public abstract void onSuccess(T resultJson);

    private int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    private String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
