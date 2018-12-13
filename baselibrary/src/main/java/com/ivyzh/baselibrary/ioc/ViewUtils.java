package com.ivyzh.baselibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.ivyzh.baselibrary.utils.NetUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewUtils {
    // 兼容Activity
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    // 兼容Fragment
    public static void inject(Fragment fragment) {
        inject(new ViewFinder(fragment), fragment);
    }

    // 兼容View
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }


    private static void inject(ViewFinder viewFinder, Object object) {
        injectFiled(viewFinder, object);//注入字段
        injectEvent(viewFinder, object);//注入事件
    }

    private static void injectEvent(ViewFinder viewFinder, Object object) {
        // 1.获取所有方法
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        // 2.获取方法上面的所有id
        for (Method method : methods) {
            // 2.1 点击事件的注解处理
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                if (viewIds.length > 0) {
                    for (int viewId : viewIds) {
                        // 3.遍历所有的id 先findViewById然后 setOnClickListener
                        View view = viewFinder.findViewById(viewId);
                        if (view != null) {
                            view.setOnClickListener(new DeclaredOnClickListener(method, object, viewFinder));

                        }
                    }
                }
            }

        }
    }

    /**
     * @ViewById(R.id.tv_msg) private TextView mMsg;//使用private修饰也可以
     */
    private static void injectFiled(ViewFinder viewFinder, Object object) {
        // object --> activity or fragment or view 是反射的类
        // viewFinder --> 只是一个view的findViewById的辅助类

        // 1. 获取所有的属性
        Class<?> clazz = object.getClass();
        // 获取所有属性包括私有和公有
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // 2. 获取属性上面ViewById的值
            ViewById viewById = field.getAnnotation(ViewById.class);

            if (viewById != null) {
                // 获取ViewById属性上的viewId值
                int viewId = viewById.value();
                // 3. 通过findViewById获取View
                View view = viewFinder.findViewById(viewId);

                if (view != null) {
                    // 4. 反射注入View属性
                    // 设置所有属性都能注入包括私有和公有
                    field.setAccessible(true);
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new RuntimeException("Invalid @ViewInject for "
                            + clazz.getSimpleName() + "." + field.getName());
                }
            }
        }

    }


    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mHandlerType;
        private ViewFinder mViewFinder;

        public DeclaredOnClickListener(Method method, Object handlerType, ViewFinder viewFinder) {
            mMethod = method;
            mHandlerType = handlerType;
            mViewFinder = viewFinder;
        }

        @Override
        public void onClick(View v) {
            CheckNet checkNet = mMethod.getAnnotation(CheckNet.class);
            if (checkNet == null) {
                // 4.反射执行方法
                mMethod.setAccessible(true);
                try {
                    mMethod.invoke(mHandlerType, v);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        mMethod.invoke(mHandlerType, null);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                Context context = mViewFinder.getContext();
                if (context == null) {
                    throw new IllegalArgumentException("inject without Context!");
                } else {
                    String msg = context.getString(checkNet.msg());
                    if (NetUtils.isNetworkConnected(context)) {//有网络
                        mMethod.setAccessible(true);
                        try {
                            mMethod.invoke(mHandlerType, v);
                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                mMethod.invoke(mHandlerType, null);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }


                    } else {//没网络 todo
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                    }
                }
            }

        }
    }

}
