package com.ivyzh.ubing.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ivyzh.baselibrary.recyclerview.imageloader.GlideImageLoader;
import com.ivyzh.baselibrary.recyclerview.imageloader.ImageLoader;
import com.ivyzh.baselibrary.recyclerview.commonadapter.CommonRecyclerAdapter;
import com.ivyzh.baselibrary.recyclerview.commonholder.CommonViewHolder;
import com.ivyzh.ubing.R;

import java.util.List;

/**
 * Created by Ivy on 2018/12/15.
 */

public class DataAdapter extends CommonRecyclerAdapter<String> {
    public DataAdapter(Context context, List<String> datas, int resId) {
        super(context, datas, resId);
    }

    @Override
    protected void convert(CommonViewHolder holder, String s, int pos) {
        holder.setText(R.id.tv_data, s);
        String mUrl = "https://docs.bmob.cn/data/Restful/h_2restful_datahelps/doc/assets/images/logo.png";
        holder.setImageUrl(R.id.iv_data, new GlideImageLoader(mUrl));
    }
}
