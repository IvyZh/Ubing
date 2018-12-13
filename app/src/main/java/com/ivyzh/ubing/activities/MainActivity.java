package com.ivyzh.ubing.activities;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.CheckNet;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.framelibrary.http.PreHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.domain.NovelBean;

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


        String url = "https://www.baidu.com";

        url = "https://www.apiopen.top/novelApi";
        HttpUtils.with(this)
                .addParam("name", "zz")
                .addParam("pwd", "123456")
                .post()
                .url(url)
                //.exchangeEngine(new XUtilsEngine())
                .execute(new PreHttpCallBack<NovelBean>() {
                    @Override
                    public void onError(Exception e) {
                        L.v("error -> " + e);

                    }

//                    @Override
//                    public void onSuccess(String result) {
//                        L.v("success -> " + result);
//                        mMsg.setText(result.toString());
//                        super.onSuccess(result);
//                    }

                    @Override
                    public void onSuccess(NovelBean resultJson) {
                        L.v("success -> " + resultJson.getData().size());
                    }


                });

    }
}
