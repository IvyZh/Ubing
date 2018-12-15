package com.ivyzh.ubing.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.http.HttpUtils;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.baselibrary.recyclerview.imageloader.GlideImageLoader;
import com.ivyzh.baselibrary.recyclerview.itemdecoration.LinearLayoutItemDecoration;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemClickListener;
import com.ivyzh.framelibrary.http.UbingHttpCallBack;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.adapter.WorkOrderAdapter;
import com.ivyzh.ubing.contants.LoginUserInfo;
import com.ivyzh.ubing.domain.WorkOrderBean;
import com.ivyzh.ubing.domain.response.UserInfoBean;
import com.ivyzh.ubing.http.Api;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.ll_main_root)
    ViewGroup mMainRootView;

    NavigationView mNavigationView;
    ImageView mIvPortrait;
    TextView mTvUserName;

    List<WorkOrderBean.ResultsBean> list;
    WorkOrderAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.ItemDecoration linearItemDecoration;
    private DrawerLayout drawerLayout;

    @Override
    protected int getContentView() {
        return R.layout.activity_main_back;
    }

    @Override
    protected void initTitle() {
        new DefaultNavigationBar.Builder(this, mMainRootView)//这里还不能用this.getApplicationContext
                .setTitle("主页")
                .hideLeftIcon()
                .setRightText("刷新")
                .setRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadWorkOrderData();//加载工单数据
                    }
                })
                .build();
    }

    @Override
    protected void initView() {
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        mIvPortrait = mNavigationView.getHeaderView(0).findViewById(R.id.iv_portrait);
        mTvUserName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_username);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearItemDecoration = new LinearLayoutItemDecoration(this, R.drawable.item_divider_middle);
        mRecyclerView.addItemDecoration(linearItemDecoration);
        list = new ArrayList<>();
        adapter = new WorkOrderAdapter(this, list, R.layout.item_workorder_info);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                startActivity(WorkOrderDescActivity.class, list.get(pos).getObjectId());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        loadWorkOrderData();//加载工单数据
        loadSelfInfo();//加载当前登录用户信息
    }

    /**
     * 加载当前登录用户信息
     */
    private void loadSelfInfo() {
        HttpUtils.with(this)
                .url(Api.CURRENT_USER_INFO.replace("{objectID}", LoginUserInfo.objectId))
                .execute(new UbingHttpCallBack<UserInfoBean>() {
                    @Override
                    public void onSuccess(UserInfoBean resultJson) {
                        new GlideImageLoader(mIvPortrait, resultJson.getPortraitUrl()).circleCrop().loadImage();
                        mTvUserName.setText(resultJson.getNickname());
                    }
                });
    }

    /**
     * 加载工单数据
     */
    private void loadWorkOrderData() {
        String bql = "select include author,* from WorkOrder order by createdAt desc";
        HttpUtils.with(this).url(Api.BQL_QUERY)
                .addParam("bql", bql)
                .execute(new UbingHttpCallBack<WorkOrderBean>() {
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
        startActivity(PublishWorkOrderActivity.class);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
            case R.id.wallet:
            case R.id.photo:
            case R.id.file:
            case R.id.navigation_daily:
            case R.id.navigation_welfare:
            case R.id.navigation_android:
            case R.id.navigation_ios:
            case R.id.navigation_js:
            case R.id.navigation_video:
            case R.id.navigation_resources:
            case R.id.navigation_app:
                break;
        }

        toast(item.getTitle().toString());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
