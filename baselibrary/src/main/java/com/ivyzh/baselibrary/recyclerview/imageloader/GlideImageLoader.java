package com.ivyzh.baselibrary.recyclerview.imageloader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

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
    public void loadImage() {
        if (mCircleCrop) {
            Glide.with(mImageView.getContext()).load(mUrl).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImageView);
        } else {
            Glide.with(mImageView.getContext()).load(mUrl).into(mImageView);
        }
    }
}
