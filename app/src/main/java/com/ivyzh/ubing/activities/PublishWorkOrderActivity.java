package com.ivyzh.ubing.activities;

import android.os.SystemClock;
import android.widget.EditText;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.baselibrary.utils.GsonUtils;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.User;
import com.ivyzh.ubing.domain.request.MyPointer;
import com.ivyzh.ubing.domain.request.WorkOrder;
import com.ivyzh.ubing.http.Api;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PublishWorkOrderActivity extends BaseActivity {

    @ViewById(R.id.et_title)
    EditText etTitle;
    @ViewById(R.id.et_content)
    EditText etContent;

    @Override
    protected int getContentView() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this)//这里还不能用this.getApplicationContext
                .setTitle("发布")
                .hideRigntIcon()
                .build();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }

    @OnClick({R.id.btn_query})
    private void query() {
        queryData();
    }

    private void queryData() {
//        String bql = "select include author,* from WorkOrder where objectId = '9444fadef3' and author = pointer('_User', '21d739f9d7')";

        String bql = "select include author,* from WorkOrder where objectId = '9444fadef3' ";

        //String bql = "select * from WorkOrder where objectId = '9444fadef3'";
//        String bql = "select * from WorkOrder ";
//        String bql = "select * from WorkOrder where publishUserId = '21d739f9d7'";
        HttpUtils.with(this).url(Api.BQL_QUERY)
//                .addParam("bql", "select * from WorkOrder")
                .addParam("bql", bql)
                .execute(new UbingHttpCallBack<String>() {
                    @Override
                    public void onSuccess(String resultJson) {

                    }
                });

    }

    @OnClick({R.id.btn_publish})
    private void publish() {
        publishWorkOrder();
    }

    private void publishWorkOrder() {

        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        HttpUtils.with(this).url(Api.WORK_ORDER)
                .addParam("publishUserId", LoginUserInfo.objectId)
                .addParam("title", title + SystemClock.currentThreadTimeMillis())
                .addParam("content", content)
                .addParam("author", generatePoint())
                .post()
                .execute(new UbingHttpCallBack<String>() {
                    @Override
                    public void onSuccess(String resultJson) {
                        toast("发布成功");
                        finish();
                    }
                });

    }


    private void method1() {
        HttpUtils.with(this).url(Api.WORK_ORDER)
                .addParam("publishUserId", LoginUserInfo.objectId)
                .addParam("title", "求xx资源 " + SystemClock.currentThreadTimeMillis())
                .addParam("content", "谢谢了！")
                .addParam("author", generatePoint())
                .post()
                .execute(new UbingHttpCallBack<String>() {
                    @Override
                    public void onSuccess(String resultJson) {

                    }
                });
    }

    private JSONObject generatePoint() {
        MyPointer pointer = new MyPointer("_User", LoginUserInfo.objectId);
        String json = GsonUtils.toJson(pointer);
        JSONObject object = null;// 必须要转成JSONObject，否则报错invalid type for key 'author', expected 'Pointer', but got 'String'.
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

}
