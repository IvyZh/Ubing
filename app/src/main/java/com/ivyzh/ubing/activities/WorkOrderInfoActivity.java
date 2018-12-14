package com.ivyzh.ubing.activities;

import android.widget.TextView;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.framelibrary.http.PreHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.domain.WorkOrderBean;
import com.ivyzh.ubing.http.Api;

import java.util.List;

/**
 * Created by Ivy on 2018/3/12.
 */

public class WorkOrderInfoActivity extends BaseActivity {


    @ViewById(R.id.tv_msg)
    TextView mtvMsg;

    StringBuilder sb = new StringBuilder();

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
        HttpUtils.with(this).url(Api.WORK_ORDER_INFO.replace("{id}", "835686f6da"))
                .get().execute(new PreHttpCallBack<WorkOrderBean.ResultsBean>() {
            @Override
            public void onSuccess(WorkOrderBean.ResultsBean resultJson) {
                if (resultJson != null) {
                    sb.append(resultJson.getTitle() + "--" + resultJson.getContent());
                    mtvMsg.setText(sb.toString());
                }
            }
        });


        HttpUtils.with(this).url(Api.WORK_ORDER_COMMENT)
//                .addParam("where", "{state=1}")
                .addParam("where", "%7B%22state%22:1%7D")
                .get().execute(new PreHttpCallBack<String>() {
            @Override
            public void onSuccess(String resultJson) {

            }
        });
    }


}
