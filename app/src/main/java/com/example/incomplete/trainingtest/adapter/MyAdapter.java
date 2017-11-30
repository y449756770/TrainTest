package com.example.incomplete.trainingtest.adapter;

/**
 * Created by incomplete on 17/7/27.
 */

//RecyclerView Adapter

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.incomplete.trainingtest.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mTitles = null;

    public static final int TYPE_FOOTER = 1;

    public static final int TYPE_ITEM = 2;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;

    //上拉加载更多状态-默认为0
    private int load_more_status = 0;


    public MyAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);

        this.mTitles = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles.add("item" + index);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new MyViewHolder(mInflater.inflate(R.layout.item_layout, parent, false));

        } else {
            return new FooterViewHolder(mInflater.inflate(R.layout.recycler_load_more_layout, parent, false));

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv.setText(mTitles.get(position));
        } else if (holder instanceof FooterViewHolder) {
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    ((FooterViewHolder) holder).foot_view_item_tv.setText("上拉加载更多...");

                    break;
                case LOADING_MORE:
                    ((FooterViewHolder) holder).foot_view_item_tv.setText("正在加载更多数据...");
                    break;
            }


        }

    }

    @Override
    public int getItemCount() {

        return mTitles.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) { //滑动到最后一个的时候
            return TYPE_FOOTER;

        } else {
            return TYPE_ITEM;

        }

    }

    public void addItems(List<String> strs) {
        strs.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(strs);
        notifyDataSetChanged();


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_info);
        }
    }

    /**
     * 底部FooterVie的ViewHolder
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_view_item_tv;

        public FooterViewHolder(View itemView) {
            super(itemView);
            foot_view_item_tv = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
        }
    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    public void addMornItems(List<String> strs) {
        mTitles.addAll(strs);
        notifyDataSetChanged();

    }
}