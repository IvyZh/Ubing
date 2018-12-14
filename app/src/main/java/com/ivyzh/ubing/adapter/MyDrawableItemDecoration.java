package com.ivyzh.ubing.adapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivyzh.baselibrary.log.L;

/**
 * Created by Ivy on 2018/12/14.
 */

public class MyDrawableItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public MyDrawableItemDecoration(Drawable divider) {
        // 利用Drawable绘制分割线
        mDivider = divider;
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        L.v("onDraw -> " + mDivider.getIntrinsicHeight());
        int childCount = parent.getChildCount();
        // 计算需要绘制的区域
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            rect.top = childView.getBottom();
            rect.bottom = rect.top + mDivider.getIntrinsicHeight();
            // 直接利用Canvas去绘制
            mDivider.draw(c);
        }


    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        L.v("getItemOffsets -> " + mDivider.getIntrinsicHeight());

        // 在每个子View的下面留出来画分割线
        outRect.bottom += mDivider.getIntrinsicHeight();
    }

}
