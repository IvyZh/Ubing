package com.ivyzh.baselibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

public class ViewFinder {
    private Activity mActivity;
    private View mView;
    private Fragment mFragment;
    private Context mContext;

    public ViewFinder(Activity mActivity) {
        this.mActivity = mActivity;
        mContext = mActivity.getApplicationContext();
    }

    public ViewFinder(View mView) {
        this.mView = mView;

    }

    public ViewFinder(Fragment mFragment) {
        this.mFragment = mFragment;
        mContext = mFragment.getActivity().getApplicationContext();

    }

    public View findViewById(int viewId) {
        if (mActivity != null) {
            return mActivity.findViewById(viewId);
        }

        if (mFragment != null) {
            return mFragment.getActivity().findViewById(viewId);
        }

        if (mView != null) {
            return mView.findViewById(viewId);
        }
        return null;

    }

    public Context getContext() {
        return mContext;
    }
}
