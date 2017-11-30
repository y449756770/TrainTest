package com.example.incomplete.trainingtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.incomplete.trainingtest.R;

import java.util.ArrayList;

;

/**
 * Created by lalo.zhang on 2015/12/20.
 */
public class CardAdapter extends BaseAdapter {
    private ArrayList<? extends Object> data;
    private Context context;
    private OnCardExitListener mListener;
    private final static int TYPE_ITEM = 0;
    private final static int TYPE_ITEM_INVITATION = 2;
    private final static int TYPE_SEPARATOR = 1;

    public CardAdapter(Context context, ArrayList<? extends Object> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnCardExitListener(OnCardExitListener listener) {
        this.mListener = listener;
    }

    public ArrayList<Object> getData() {
        return (ArrayList<Object>) data;
    }


//    public void removeCardItem(ConnectionCard card) {
//        if (data != null) {
//            data.remove(card);
//            notifyDataSetChanged();
//        }
//    }


    @Override
    public int getCount() {
        if (data != null && !data.isEmpty()) {
            return data.size();
        }
        return 0;
    }


    @Override
    public Object getItem(int position) {
        if (data != null && !data.isEmpty() && data.size() > position) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fliping_card_item, parent, false);
//                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
//                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
//                holder.dq = (TextView) convertView.findViewById(R.id.tv_des_dq);
//                holder.tvPositionCompanyLoc = (TextView) convertView.findViewById(R.id.tv_position_company_loc);
//                holder.tvDes = (TextView) convertView.findViewById(R.id.tv_des);
//                holder.tvMessage = (TextView) convertView.findViewById(R.id.tv_message);
//                holder.icon = (ImageView) convertView.findViewById(R.id.iv_pic);
//                holder.ivIgnore = (TextView) convertView.findViewById(R.id.iv_dislike);
//                holder.ivPass = (TextView) convertView.findViewById(R.id.iv_like);
//                holder.ivVip = (ImageView) convertView.findViewById(R.id.iv_vip_view);
//                holder.lietouIcon = (ImageView) convertView.findViewById(R.id.lietou_icon);
//                holder.mMessageLayout = (LinearLayout) convertView.findViewById(R.id.message_layout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            return convertView;



    }

    private static class ViewHolder {

        public TextView tvTitle;
        public TextView tvDes;
        public TextView tvName;
        public TextView tvPositionCompanyLoc;
        public TextView tvMessage;
        public ImageView icon;
        public TextView ivIgnore;
        public TextView ivPass;
        public ImageView ivVip;
        public ImageView lietouIcon;
        public LinearLayout mMessageLayout;
        public TextView dq;
    }


    private static class ViewInvitationHolder {

        public ImageView mUserIcon;
        public ImageView mVipIcon;
        public TextView mUserName;
        public TextView mUserPosition;
        public TextView mUserCommonFriends;
        public TextView mDescribeView;
        public TextView mIgnoreView;
        public TextView mInvitationView;
    }

    public interface OnCardExitListener {
        void onLeftExit(Object item);

        void onRightExit(Object item);
    }
}
