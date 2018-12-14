package com.ivyzh.ubing.activities;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.baselibrary.recyclerview.itemdecoration.GridLayoutItemDecoration;
import com.ivyzh.baselibrary.recyclerview.itemdecoration.LinearLayoutItemDecoration;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemClickListener;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemLongClickListener;
import com.ivyzh.baselibrary.utils.DataUtils;
import com.ivyzh.framelibrary.http.PreHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.adapter.DataAdapter;
import com.ivyzh.ubing.domain.WorkOrderBean;
import com.ivyzh.ubing.http.Api;

import java.util.List;

public class MainActivity extends BaseActivity {


    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    List<String> list;
    DataAdapter adapter;
    boolean isGridLayout = true;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView.ItemDecoration gridItemDecoration;
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
                .setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isGridLayout) {
                            //mRecyclerView.addItemDecoration(linearItemDecoration);
                            mRecyclerView.setLayoutManager(linearLayoutManager);
                        } else {
                            //mRecyclerView.addItemDecoration(gridItemDecoration);
                            mRecyclerView.setLayoutManager(gridLayoutManager);
                        }
                        isGridLayout = !isGridLayout;

                    }
                })
                .build();

    }

    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        gridItemDecoration = new GridLayoutItemDecoration(this, R.drawable.item_divider);
        linearItemDecoration = new LinearLayoutItemDecoration(this, R.drawable.item_divider);
        mRecyclerView.addItemDecoration(linearItemDecoration);

        list = DataUtils.generateData(0);

        adapter = new DataAdapter(this, list, R.layout.item_image_textview);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                toast("click  " + pos);
                startActivity(WorkOrderInfoActivity.class);
            }
        });

        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int pos) {
                toast("long click  " + pos);
                return true;
            }


        });
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        HttpUtils.with(this).url(Api.WORK_ORDER)
//                .addParam("where", "{state=1}")
//                .addParam("where", "%7B%22state%22:1%7D")
                .get().execute(new PreHttpCallBack<WorkOrderBean>() {
            @Override
            public void onSuccess(WorkOrderBean resultJson) {
                if (resultJson.getResults() != null && resultJson.getResults().size() > 0) {
                    list.clear();
                    List<WorkOrderBean.ResultsBean> results = resultJson.getResults();

                    for (WorkOrderBean.ResultsBean r : results) {
                        list.add(r.getTitle() + "----" + r.getContent());
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


    @OnClick(R.id.btn_msg)
    private void click() {
        startActivity(FeedBackActivity.class);
    }
}
