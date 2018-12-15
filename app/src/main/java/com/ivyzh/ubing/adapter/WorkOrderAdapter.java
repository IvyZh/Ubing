package com.ivyzh.ubing.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ivyzh.baselibrary.recyclerview.imageloader.GlideImageLoader;
import com.ivyzh.baselibrary.recyclerview.imageloader.GlideInstance;
import com.ivyzh.baselibrary.recyclerview.imageloader.ImageLoader;
import com.ivyzh.baselibrary.recyclerview.commonadapter.CommonRecyclerAdapter;
import com.ivyzh.baselibrary.recyclerview.commonholder.CommonViewHolder;
import com.ivyzh.ubing.R;
import com.ivyzh.ubing.domain.WorkOrderBean;

import java.util.List;

/**
 * Created by Ivy on 2018/12/15.
 */

public class WorkOrderAdapter extends CommonRecyclerAdapter<WorkOrderBean.ResultsBean> {
    public WorkOrderAdapter(Context context, List<WorkOrderBean.ResultsBean> datas, int resId) {
        super(context, datas, resId);
    }

    @Override
    protected void convert(CommonViewHolder holder, WorkOrderBean.ResultsBean data, int pos) {
        holder.setText(R.id.tv_title, data.getTitle());
        holder.setText(R.id.tv_content, data.getContent());
        if (data.getAuthor() != null) {
            holder.setImageUrl(R.id.iv_portrait, data.getAuthor().getPortraitUrl());
            holder.setText(R.id.tv_name, data.getAuthor().getNickname());
        }

    }
}
