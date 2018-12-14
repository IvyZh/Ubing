package com.ivyzh.baselibrary.navagationbar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ivyzh.baselibrary.R;

/**
 * Created by Ivy on 2018/12/14.
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder> {
    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void bindParams() {
        super.bindParams();
        //findViewById(R.id.tv_left).setVisibility(getBuilder().mLeftVisible);//使用泛型解决报错问题
        findViewById(R.id.iv_back).setVisibility(getBuilder().mLeftVisible);
        findViewById(R.id.btn_confirm).setVisibility(getBuilder().mRightVisible);

        View.OnClickListener listener = getBuilder().mOnClickListenerMaps.get(R.id.btn_confirm);
        if (listener == null) {
            findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getBuilder().mContext).finish();
                }
            });
        }

    }

    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder> {

        int mLeftVisible = View.VISIBLE;
        int mRightVisible = View.VISIBLE;

        public Builder(Context context) {
            super(context, R.layout.layout_default_title, null);
        }

        public Builder(Context context, ViewGroup parent) {
            super(context, R.layout.layout_default_title, parent);
        }

        @Override
        public AbsNavigationBar build() {
            return new DefaultNavigationBar(this);
        }

        public Builder setTitle(String title) {
            setText(R.id.tv_title, title);
            return this;
        }


        public Builder setRightText(String title) {
            setText(R.id.btn_confirm, title);
            return this;
        }

        public Builder setRightListener(View.OnClickListener listener) {
            setOnClickListener(R.id.btn_confirm, listener);
            return this;
        }

        public Builder hideLeftIcon() {
            mLeftVisible = View.INVISIBLE;
            return this;
        }

        public Builder showLeftICon() {
            mLeftVisible = View.VISIBLE;
            return this;
        }

        public Builder hideRigntIcon() {
            mRightVisible = View.INVISIBLE;
            return this;
        }

        public Builder showRigntICon() {
            mRightVisible = View.VISIBLE;
            return this;
        }
    }

}
