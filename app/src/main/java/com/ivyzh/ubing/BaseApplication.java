package com.ivyzh.ubing;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this.getApplicationContext(), KeyManager.BMOB_APP_KEY);
    }


}
