package com.ivyzh.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ivyzh.baselibrary.ioc.ViewUtils;

public abstract class BaseActivity extends AppCompatActivity {
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

}