package com.ivyzh.baselibrary.navagationbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivyzh.baselibrary.log.L;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivy on 2018/12/14.
 */

public abstract class AbsNavigationBar<E extends AbsNavigationBar.Builder> implements INavigation {

    E mBuilder;
    View mNavigationBarView;

    public E getBuilder() {
        return mBuilder;
    }

    protected AbsNavigationBar(E builder) {
        this.mBuilder = builder;

        // 下面三个方法，是实现INavigation这个接口的三个方法，其实不写实现也可以的。
        // 创建布局
        inflateView();
        // 加入到父布局
        addToRootView();
        // 绑定数据
        bindParams();
    }

    @Override
    public void inflateView() {


        if (mBuilder.mParent == null) {
            ViewGroup activityRoot = ((Activity) mBuilder.mContext).findViewById(android.R.id.content);
            mBuilder.mParent = (ViewGroup) activityRoot.getChildAt(0);
        }

        mNavigationBarView = LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.mLayoutId, mBuilder.mParent, false);

    }

    @Override
    public void addToRootView() {
        mBuilder.mParent.addView(mNavigationBarView, 0);
    }


    @Override
    public void bindParams() {

        // 绑定文本参数
        Map<Integer, CharSequence> textMaps = mBuilder.mTextMaps;
        for (Map.Entry<Integer, CharSequence> entry : textMaps.entrySet()) {
            Integer viewId = entry.getKey();
            CharSequence value = entry.getValue();
            TextView tv = findViewById(viewId);
            tv.setText(value);
        }

        // 绑定点击事件
        Map<Integer, View.OnClickListener> listenerMaps = mBuilder.mOnClickListenerMaps;
        for (Map.Entry<Integer, View.OnClickListener> entry : listenerMaps.entrySet()) {
            Integer viewId = entry.getKey();
            View.OnClickListener listener = entry.getValue();
            findViewById(viewId).setOnClickListener(listener);
        }

    }

    public <V extends View> V findViewById(int viewId) {
        return (V) mNavigationBarView.findViewById(viewId);
    }

    // 构建AbsNavigationBar还有存储参数
    public static abstract class Builder<T extends Builder> {
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        Map<Integer, CharSequence> mTextMaps;
        Map<Integer, View.OnClickListener> mOnClickListenerMaps;

        public Builder(Context context, int layoutId, ViewGroup parent) {
            mContext = context;
            mLayoutId = layoutId;
            mParent = parent;
            mTextMaps = new HashMap<>();
            mOnClickListenerMaps = new HashMap<>();
        }

        // 用来创建 AbsNavigationBar
        public abstract AbsNavigationBar build();

        public T setText(int viewId, String msg) {//使用泛型解决报错问题
            mTextMaps.put(viewId, msg);
            return (T) this;
        }

        public T setOnClickListener(int viewId, View.OnClickListener listener) {
            mOnClickListenerMaps.put(viewId, listener);
            return (T) this;
        }
    }
}

