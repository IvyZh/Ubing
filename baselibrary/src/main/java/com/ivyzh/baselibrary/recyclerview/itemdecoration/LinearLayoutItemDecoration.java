package com.ivyzh.baselibrary.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ivy on 2018/12/14.
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration {
    Drawable mDrawableDivider;

    public LinearLayoutItemDecoration(Context context, int resId) {
        mDrawableDivider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            outRect.top = mDrawableDivider.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            rect.bottom = childView.getTop();
            rect.top = rect.bottom - mDrawableDivider.getIntrinsicHeight();
            mDrawableDivider.setBounds(rect);
            mDrawableDivider.draw(c);
        }


    }
}
