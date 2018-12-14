package com.ivyzh.ubing.activities.test;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ivyzh.baselibrary.base.BaseActivity;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.log.L;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.domain.Person;
import com.ivyzh.ubing.domain.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class TestActivity extends BaseActivity {
    @ViewById(R.id.tv_msg)
    TextView tvMsg;

    @Override
    protected int getContentView() {
        return R.layout.activity_test;
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


    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query, R.id.btn_query_all, R.id.btn_other})
    private void btn(View view) {
        int id = view.getId();
        tvMsg.setText(id + "");
        switch (id) {
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_query_all:
                queryAll();
                break;

            case R.id.btn_other:
                otherOperate();
                break;
        }
    }

    private void otherOperate() {

        signUp();
    }


    //-----------------------------------------------------------------------------

    /**
     * 账号密码注册
     */
    private void signUp() {
        final User user = new User();
        user.setUsername("zz");
        user.setPassword("123456");
        user.setNickname("ivy");
        user.setGender(0);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    toast("注册成功" + user.getObjectId());
                } else {
                    toast("注册失败:" + e);

                }
            }
        });
    }


    //-----------------------------------------------------------------------------

    private void queryAll() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.order("-createdAt")
                .findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> object, BmobException e) {

                        L.v("e -> " + e);
                        //L.v(object.toString());
                        if (e == null) {
                            // ...
                            toast("查询成功：" + object.size());
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < object.size(); i++) {
                                sb.append(object.get(i).toString()).append("\n");
                            }

                            tvMsg.setText(sb.toString());

                        } else {
                            // ...

                        }
                    }
                });
    }

    private void query() {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("3ddd1ac5d5", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if (e == null) {
                    toast("查询成功");
                    tvMsg.setText(object.toString());
                } else {
                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void update() {
        //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("3ddd1ac5d5", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    toast("更新成功:" + p2.getUpdatedAt());
                } else {
                    toast("更新失败：" + e.getMessage());
                }
            }

        });
    }

    private void delete() {
        final Person p2 = new Person();
        p2.setObjectId("3ddd1ac5d5");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    toast("删除成功:" + p2.getUpdatedAt());
                } else {
                    toast("删除失败：" + e.getMessage());
                }
            }

        });
    }

    private void add() {
        Person p2 = new Person();
        p2.setName("lili");
        p2.setAge(22);
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("添加数据成功，返回objectId为：" + objectId);
                } else {
                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });
    }


    //-----------------------------------------------------------------------------
}
