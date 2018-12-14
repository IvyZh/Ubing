package com.ivyzh.baselibrary.recyclerview.imageloader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Ivy on 2018/12/15.
 */

public class GlideImageLoader extends ImageLoader {
    public GlideImageLoader(String url) {
        super(url);
    }

    public GlideImageLoader(ImageView imageView, String url) {
        super(imageView, url);
    }

    @Override
    public void loadImage(ImageView imageView) {
        Glide.with(imageView.getContext()).load(mUrl).into(imageView);
    }
}
