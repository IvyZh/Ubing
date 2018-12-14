package com.ivyzh.ubing.app;

import android.app.Application;

import com.ivyzh.framelibrary.http.KeyManager;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this.getApplicationContext(), KeyManager.BMOB_APP_KEY);
    }


}
