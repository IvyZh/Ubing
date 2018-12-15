package com.ivyzh.ubing.activities;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.baselibrary.utils.GsonUtils;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.base.BaseSkinActivity;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.WorkOrderBean;
import com.ivyzh.ubing.domain.request.MyPointer;
import com.ivyzh.ubing.domain.request.MyRelation;
import com.ivyzh.ubing.domain.response.BaseResult;
import com.ivyzh.ubing.http.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 工单详情
 */

public class WorkOrderDescActivity extends BaseSkinActivity {


    @ViewById(R.id.tv_title)
    TextView mtvTitle;
    @ViewById(R.id.tv_content)
    TextView mtvContent;
    @ViewById(R.id.tv_comments)
    TextView mtvComments;
    @ViewById(R.id.et_comment)
    EditText mEtComment;

    String mWorkOderId;

    @Override
    protected int getContentView() {
        return R.layout.activity_wo_info;
    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this)//这里还不能用this.getApplicationContext
                .setTitle("详情")
                .hideRigntIcon()
                .build();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mWorkOderId = getIntent().getStringExtra("id");
        L.v("mWorkOderId:" + mWorkOderId);

        /**
         * 加载工单数据
         */
        String bql = "select include comments,author,* from WorkOrder where objectId = '" + mWorkOderId + "'";
        HttpUtils.with(this).url(Api.BQL_QUERY)
                .addParam("bql", bql)
                .execute(new UbingHttpCallBack<WorkOrderBean>() {
                    @Override
                    public void onSuccess(WorkOrderBean resultJson) {
                        mtvTitle.setText(resultJson.getResults().get(0).getTitle());
                        mtvContent.setText(resultJson.getResults().get(0).getContent());
                    }
                });


        // 加载回复
   /*     String bql2 = "select * from CommentWorkOrder where workOrderId = '" + mWorkOderId + "'";
        HttpUtils.with(this).url(Api.BQL_QUERY)
                .addParam("bql", bql2)
                .execute(new UbingHttpCallBack<String>() {
                    @Override
                    public void onSuccess(String resultJson) {
                        mtvComments.setText(resultJson);
                    }
                });*/

    }


    public void addComment(View view) {
        toast("addComment");
        add();
    }

    private void add() {
        String commentContent = mEtComment.getText().toString().trim();
        // 添加回复应该有两个操作、1. 插入CommentWorkOrder表。2.将WorkOrder 与之关联

        // 第一步：将数据插入CommentWorkOrder表中
        HttpUtils.with(this)
                .url(Api.WORK_ORDER_COMMENT)
                .addParam("content", commentContent + SystemClock.currentThreadTimeMillis())
                .addParam("author", generatePoint("_User", LoginUserInfo.objectId))
                .addParam("workOrder", generatePoint("WorkOrder", mWorkOderId))
                .post()
                .execute(new UbingHttpCallBack<BaseResult>() {
                    @Override
                    public void onSuccess(BaseResult resultJson) {
                        //{"createdAt":"2018-12-16 02:23:28","objectId":"ec77c09ec5"}
                        String objectId = resultJson.getObjectId();
                        if (!TextUtils.isEmpty(objectId)) {
                            toast("回复成功");
                            // 第二步：与WorkOrder表中comments字段关联
                            connectWorkOrder(objectId);

                        } else {

                        }
                    }
                });


    }


    /**
     * 与WorkOrder表中comments字段关联
     *
     * @param objectId 评论id
     */
    private void connectWorkOrder(String objectId) {

        HttpUtils.with(this)
                .url(Api.WORK_ORDER_INFO.replace("{id}", mWorkOderId))
                .addParam("comments", generateRelation("CommentWorkOrder", objectId))
                .put()
                .execute(new UbingHttpCallBack<String>() {
                    @Override
                    public void onSuccess(String resultJson) {
                        toast("管理成功");
                    }
                });
    }


}
