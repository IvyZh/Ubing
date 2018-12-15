package com.ivyzh.ubing.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ivyzh.baselibrary.recyclerview.imageloader.GlideImageLoader;
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
    protected void convert(CommonViewHolder holder, WorkOrderBean.ResultsBean date, int pos) {
        holder.setText(R.id.tv_data, date.getTitle());
    }
}
