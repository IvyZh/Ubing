package com.ivyzh.ubing.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.baselibrary.utils.GsonUtils;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.BaseModel;
import com.ivyzh.ubing.domain.request.MyPointer;
import com.ivyzh.ubing.http.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
//                login2();
                break;
            case R.id.tv_register:
                startActivityFinshSelf(RegisterActivity.class);
                break;
            case R.id.tv_protocol:
                break;
        }
    }

    private void login2() {
        BmobUser bu2 = new BmobUser();
        bu2.setUsername("zz");
        bu2.setPassword("123456");
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    toast("登录成功:");
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    startActivityFinshSelf(MainActivity.class);
                } else {

                }
            }
        });


    }

    private void login() {
        boolean isTure = false;
        if (isTure) {

/*            HashMap<String, Object> map = new HashMap<>();
            map.put("key1", "value");
            map.put("key2", 123);
            MyPointer pointer = new MyPointer("name", "id");
            String s = GsonUtils.toJson(pointer);
            try {
                JSONObject jo = new JSONObject(s);
                map.put("key3", jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject json = new JSONObject(map);
            RequestBody body = FormBody.create(MediaType.parse("application/json"), json.toString());
            L.v(" finall - >" + json);
            L.v(" RequestBody - >" + body);*/


            startActivityFinshSelf(MainActivity.class);
            return;
        }

        toast("login");

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
