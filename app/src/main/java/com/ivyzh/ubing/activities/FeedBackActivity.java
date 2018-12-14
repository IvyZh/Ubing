package com.ivyzh.ubing.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.framelibrary.http.DeviceInfoHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.BaseModel;
import com.ivyzh.ubing.http.Api;

/**
 * Created by Ivy on 2018/3/12.
 */

public class FeedBackActivity extends BaseActivity {


    @ViewById(R.id.et_feedback)
    EditText mtvFeedBack;


    @Override
    protected int getContentView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this)//这里还不能用this.getApplicationContext
                .setTitle("建议与反馈")
                .hideRigntIcon()
                .build();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    public void submit(View v) {
        String msg = mtvFeedBack.getText().toString().trim();

        if (TextUtils.isEmpty(msg)) {
            toast("反馈内容不能为空");
            return;
        }

        String publishUserId = LoginUserInfo.objectId;
        HttpUtils.with(this).url(Api.FEED_BACK)
                .addParam("publishUserId", publishUserId)
                .addParam("content", msg)
                .post()
                .execute(new DeviceInfoHttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccess(BaseModel resultJson) {
                        if (!TextUtils.isEmpty(resultJson.getObjectId())) {
                            toast("反馈成功" + resultJson.getObjectId());
                            finish();
                        } else {
                            toast("反馈失败：" + resultJson.getError());
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }


}
