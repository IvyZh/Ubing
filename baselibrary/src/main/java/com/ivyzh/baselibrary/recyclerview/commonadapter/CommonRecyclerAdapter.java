package com.ivyzh.baselibrary.recyclerview.commonadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivyzh.baselibrary.recyclerview.commonholder.CommonViewHolder;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemClickListener;
import com.ivyzh.baselibrary.recyclerview.listener.OnItemLongClickListener;

import java.util.List;

/**
 * Created by Ivy on 2018/12/14.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private LayoutInflater mInflater;
    private int mLayoutResId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommonRecyclerAdapter(Context context, List<T> datas, int resId) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
        mLayoutResId = resId;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(mLayoutResId, viewGroup, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, final int pos) {
        convert(holder, mDatas.get(pos), pos);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(pos);
                }
                return false;
            }
        });

    }

    protected abstract void convert(CommonViewHolder holder, T t, int pos);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }
}