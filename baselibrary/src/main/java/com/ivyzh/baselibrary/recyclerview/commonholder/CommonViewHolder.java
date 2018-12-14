package com.ivyzh.baselibrary.recyclerview.commonholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivyzh.baselibrary.recyclerview.imageloader.ImageLoader;

/**
 * Created by Ivy on 2018/12/15.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    // 减少findviewById和泛型转换
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //封装通用的功能如setText
    public CommonViewHolder setText(int viewId, String msg) {
        TextView textView = getView(viewId);
        textView.setText(msg);
        return this;
    }

    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView textView = getView(viewId);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CommonViewHolder setImageUrl(int viewId, ImageLoader loader) {
        ImageView imageView = getView(viewId);
        loader.loadImage(imageView);
        return this;
    }
}
