package com.ivyzh.ubing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.BaseModel;
import com.ivyzh.baselibrary.http.HttpCallBack;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.http.impl.XUtilsEngine;
import com.ivyzh.baselibrary.ioc.CheckNet;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.ioc.ViewUtils;
import com.ivyzh.baselibrary.log.L;

public class MainActivity extends BaseActivity {

    @ViewById(R.id.tv_msg)
    private TextView mMsg;

    private Button mBtnMsg;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mMsg.setText("mMsg initView");
        mBtnMsg = findViewById(R.id.btn_msg);
    }

    @Override
    protected void initData() {
        mBtnMsg.setText("click ");

    }


    @OnClick(R.id.tv_msg)
    @CheckNet(msg = R.string.net_invisible)
    private void hello() {
        Toast.makeText(this, "Hello.", Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.btn_msg)
    private void hello2() {


        HttpUtils.with(this).addParam("name", "zz")
                .url("www.baidu.com")
                .exchangeEngine(new XUtilsEngine())
                .get()
                .execute(new HttpCallBack() {
                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onSuccess(Object result) {

                    }
                });

    }
}
