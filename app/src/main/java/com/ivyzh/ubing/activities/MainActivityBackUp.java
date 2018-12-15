package com.ivyzh.ubing.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.baselibrary.recyclerview.itemdecoration.LinearLayoutItemDecoration;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemClickListener;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.adapter.WorkOrderAdapter;
import com.ivyzh.ubing.domain.WorkOrderBean;
import com.ivyzh.ubing.http.Api;

import java.util.ArrayList;
import java.util.List;

public class MainActivityBackUp extends BaseActivity {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    List<WorkOrderBean.ResultsBean> list;
    WorkOrderAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.ItemDecoration linearItemDecoration;

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
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearItemDecoration = new LinearLayoutItemDecoration(this, R.drawable.item_divider);
        mRecyclerView.addItemDecoration(linearItemDecoration);
        list = new ArrayList<>();
        adapter = new WorkOrderAdapter(this, list, R.layout.item_image_textview);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                startActivity(WorkOrderInfoActivity.class);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        HttpUtils.with(this).url(Api.WORK_ORDER)
                .addParam("where", "%7B%22state%22:1%7D")
                .get().execute(new UbingHttpCallBack<WorkOrderBean>() {
            @Override
            public void onSuccess(WorkOrderBean resultJson) {
                if (resultJson.getResults() != null && resultJson.getResults().size() > 0) {
                    list.clear();
                    List<WorkOrderBean.ResultsBean> results = resultJson.getResults();
                    if (results != null && results.size() > 0) {
                        list.addAll(results);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


    @OnClick(R.id.float_button)
    private void click() {
        startActivity(PublishWOActivity.class);
    }
}
