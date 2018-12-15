package com.ivyzh.baselibrary.recyclerview.imageloader;

import android.widget.ImageView;

/**
 * Created by Ivy on 2018/12/15.
 */

public abstract class ImageLoader {
    String mUrl;
    ImageView mImageView;
    boolean mCircleCrop;

    public ImageLoader(String url) {
        mUrl = url;
    }

    public ImageLoader(ImageView imageView, String url) {
        mUrl = url;
        mImageView = imageView;
    }

    public ImageLoader circleCrop() {
        mCircleCrop = true;
        return this;
    }

    public abstract void loadImage();
}
