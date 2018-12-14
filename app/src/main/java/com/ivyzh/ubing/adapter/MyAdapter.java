package com.ivyzh.ubing.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivyzh.ubing.R;

import java.util.List;

/**
 * Created by Ivy on 2018/12/14.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    /***************
     * 给条目设置点击和长按事件
     *********************/
    public OnItemClickListener mItemClickListener;
    public OnLongClickListener mLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public interface OnLongClickListener {
        public boolean onLongClick(int position);
    }


    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }


    /**
     * 创建条目ViewHolder
     *
     * @param parent   RecyclerView
     * @param viewType view的类型可以用来显示多列表布局等等
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * 绑定ViewHolder设置数据
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.tvData.setText(mList.get(position));

        // 设置点击和长按事件
        if (mItemClickListener != null) {
            viewHolder.tvData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        if (mLongClickListener != null) {
            viewHolder.tvData.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mLongClickListener.onLongClick(position);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * RecyclerView的Adapter需要一个ViewHolder必须要extends RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 在创建的时候利用传递过来的View去findViewById
            tvData = itemView.findViewById(R.id.tv_data);
        }
    }
}
