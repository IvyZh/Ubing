package com.ivyzh.baselibrary.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ivy on 2018/12/14.
 */

public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {
    Drawable mDrawableDivider;

    public GridLayoutItemDecoration(Context context, int resId) {
        mDrawableDivider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int right = mDrawableDivider.getIntrinsicHeight();
        int bottom = mDrawableDivider.getIntrinsicHeight();

       if (isLastRow(view, parent)) {//最后一行
            bottom = 0;
        }
        if (isLastColumn(view, parent)) {//最后一列
            right = 0;
        }
        outRect.right = right;
        outRect.bottom = bottom;

    }

    private boolean isLastColumn(View view, RecyclerView parent) {
        int currentItemPos = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            return (currentItemPos + 1) % spanCount == 0;
        }
        return true;
    }

    private boolean isLastRow(View view, RecyclerView parent) {
        int currentItemPos = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int spanCount = 1;
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            spanCount = gridLayoutManager.getSpanCount();
        }

        int rowNumber = (parent.getAdapter().getItemCount() % spanCount == 0) ?
                parent.getAdapter().getItemCount() / spanCount : parent.getAdapter().getItemCount() / spanCount + 1;

        return currentItemPos + 1 > (rowNumber - 1) * spanCount;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        Rect rect = new Rect();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            rect.left = childView.getLeft() - params.leftMargin;
            rect.right = childView.getRight() + mDrawableDivider.getIntrinsicHeight() + params.leftMargin;
            rect.top = childView.getBottom() + params.bottomMargin;
            rect.bottom = rect.top + mDrawableDivider.getIntrinsicHeight();
            mDrawableDivider.setBounds(rect);
            mDrawableDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        Rect rect = new Rect();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            rect.left = childView.getRight() + params.rightMargin;
            rect.right = rect.left + mDrawableDivider.getIntrinsicHeight();
            rect.top = childView.getTop() - params.topMargin;
            rect.bottom = childView.getBottom() + mDrawableDivider.getIntrinsicHeight() + params.bottomMargin;
            mDrawableDivider.setBounds(rect);
            mDrawableDivider.draw(c);
        }
    }


}
