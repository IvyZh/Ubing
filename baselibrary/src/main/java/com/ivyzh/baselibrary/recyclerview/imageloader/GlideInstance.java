package com.ivyzh.baselibrary.recyclerview.imageloader;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Ivy on 2018/12/15.
 */

public class GlideInstance {
    public static void load(ImageView imageView, String url) {
        load(imageView, url, false);
    }

    public static void load(ImageView imageView, String url, boolean isCircleCrop) {
        if (TextUtils.isEmpty(url))
            return;
        if (isCircleCrop) {
            Glide.with(imageView.getContext()).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
