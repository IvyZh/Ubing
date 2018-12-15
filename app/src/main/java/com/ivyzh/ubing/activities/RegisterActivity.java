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
import com.ivyzh.ubing.domain.BaseModel;
import com.ivyzh.ubing.http.Api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {

    @ViewById(R.id.et_username)
    EditText mEtUserName;
    @ViewById(R.id.et_email)
    EditText mEtMail;
    @ViewById(R.id.et_pwd)
    EditText mEtPwd;
    @ViewById(R.id.et_pwd2)
    EditText mEtPwd2;


    @Override
    protected int getContentView() {
        return R.layout.activity_register;
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


    @OnClick({R.id.bt_register, R.id.tv_login, R.id.tv_protocol, R.id.tv_change_register_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                register();
                break;
            case R.id.tv_login:
                startActivityFinshSelf(LoginActivity.class);
                break;
            case R.id.tv_change_register_type://手机号快速注册&账号注册

                break;
            case R.id.tv_protocol:
                break;

        }
    }

    /**
     * 注册
     */
    private void register() {
        String userName = mEtUserName.getText().toString().trim();
        String email = mEtMail.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        String pwd2 = mEtPwd2.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            toast("用户名不能为空");
            return;
        }

        String ruler = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        final Pattern regex = Pattern.compile(ruler);
        Matcher matcher = regex.matcher(email);
        if (!matcher.matches()) {
            toast("邮箱格式不正确");
            return;
        }


        if (!TextUtils.equals(pwd, pwd2)) {
            toast("两次密码不一致");
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 12) {
            toast("密码长度请限制在6-12位");
            return;
        }

        HttpUtils.with(this).post()
                .url(Api.REGISTER)
                .addParam("username", userName)
                .addParam("password", pwd)
                .post()
                .execute(new UbingHttpCallBack<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel resultJson) {
                        if (!TextUtils.isEmpty(resultJson.getObjectId())) {
                            toast("注册成功");
                        } else {
                            toast("注册失败：" + resultJson.getError());
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }
}
