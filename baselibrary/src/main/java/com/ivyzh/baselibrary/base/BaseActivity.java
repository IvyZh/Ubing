package com.ivyzh.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ivyzh.baselibrary.ioc.ViewUtils;

public abstract class BaseActivity extends AppCompatActivity {
    public static Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ViewUtils.inject(this);
        initTitle();//初始化头部
        initView();//初始化界面
        initData();//初始化数据
    }

    protected abstract int getContentView();

    protected abstract void initTitle();

    protected abstract void initView();

    protected abstract void initData();


    protected void startActivity(Class<? extends Activity> clz) {
        startActivity(new Intent(this, clz));
    }


    protected void toast(final String msg) {
        if (mToast == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mToast = Toast.makeText(BaseActivity.this, "", Toast.LENGTH_SHORT);
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg);
                mToast.show();
            }
        });
    }

}
