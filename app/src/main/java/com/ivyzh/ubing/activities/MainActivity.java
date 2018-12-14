package com.ivyzh.ubing.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.adapter.MyAdapter;
import com.ivyzh.ubing.adapter.MyDrawableItemDecoration;
import com.ivyzh.ubing.adapter.MyItemDecoration;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        //分割线
        //mRecyclerView.addItemDecoration(new MyItemDecoration(getResources().getColor(R.color.bottom_blue)));
        mRecyclerView.addItemDecoration(new MyDrawableItemDecoration(getResources().getDrawable(R.drawable.item_divider)));

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("data " + i);
        }

        MyAdapter adapter = new MyAdapter(this, list);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toast("click : " + position);
            }
        });
        adapter.setOnLongClickListener(new MyAdapter.OnLongClickListener() {
            @Override
            public boolean onLongClick(int position) {
                toast("long click : " + position);
                return true;
            }
        });
        mRecyclerView.setAdapter(adapter);

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
