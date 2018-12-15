package com.ivyzh.ubing.activities;

import android.view.View;
import android.widget.TextView;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.baselibrary.navagationbar.DefaultNavigationBar;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.domain.CommentWorkOrder;
import com.ivyzh.ubing.domain.Person;
import com.ivyzh.ubing.domain.User;
import com.ivyzh.ubing.domain.WorkOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class PublishWOActivity extends BaseActivity {

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


    @OnClick({R.id.btn_publish})
    private void publish() {
        publishWorkOrder();
    }

    private void publishWorkOrder() {
        if (BmobUser.isLogin()) {
            String publishUserId = BmobUser.getCurrentUser(User.class).getObjectId();
            WorkOrder workOrder = new WorkOrder("求xx资源", "谢谢", publishUserId);
            workOrder.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        toast("添加数据成功，返回objectId为：" + objectId);
                    } else {
                        toast("创建数据失败：" + e.getMessage());
                    }
                }
            });
        } else {
            toast("未登录");
        }
    }

}
