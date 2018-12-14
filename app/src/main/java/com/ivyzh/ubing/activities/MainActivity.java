package com.ivyzh.ubing.activities;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.ioc.CheckNet;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.ubing.R;

public class MainActivity extends BaseActivity {

    @ViewById(R.id.tv_msg)
    private TextView mMsg;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this)//这里还不能用this.getApplicationContext
                .setTitle("主页")
                .hideLeftIcon()
                .hideRigntIcon()
                .build();
    }

    @Override
    protected void initView() {
        mMsg.setText("主页");
    }

    @Override
    protected void initData() {
    }


    @OnClick(R.id.btn_msg)
    private void click() {
        toast("click");
        startActivity(FeedBackActivity.class);

    }
}
