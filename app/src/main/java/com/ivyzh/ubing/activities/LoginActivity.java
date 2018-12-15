package com.ivyzh.ubing.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.BaseModel;
import com.ivyzh.ubing.http.Api;

public class LoginActivity extends BaseActivity {

    @ViewById(R.id.et_username)
    EditText mEtUserName;
    @ViewById(R.id.et_pwd)
    EditText mEtPwd;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_forget_pwd, R.id.bt_login, R.id.tv_register, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                break;
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_register:
                startActivityFinshSelf(RegisterActivity.class);
                break;
            case R.id.tv_protocol:
                break;
        }
    }

    private void login() {
        boolean isTure = false;
        if (isTure) {
            startActivityFinshSelf(MainActivity.class);
            return;
        }

        String username = mEtUserName.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            toast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            toast("密码不能为空");
            return;
        }

        HttpUtils.with(this).url(Api.LOGIN)
                .addParam("username", username)
                .addParam("password", pwd)
                .execute(new UbingHttpCallBack<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel resultJson) {
                        if (!TextUtils.isEmpty(resultJson.getObjectId())) {
                            toast("登陆成功" + resultJson.getObjectId());
                            LoginUserInfo.objectId = resultJson.getObjectId();
                            startActivityFinshSelf(MainActivity.class);
                        } else {
                            toast("登陆失败：" + resultJson.getError());
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }
}
