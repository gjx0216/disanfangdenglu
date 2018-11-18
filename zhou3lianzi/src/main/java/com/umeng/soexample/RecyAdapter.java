package com.umeng.soexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Entity.ResultsBean> mList;

    public RecyAdapter(Context context, List<Entity.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            Picasso.with(mContext).load(mList.get(i).getUrl()).into(((MyViewHolder) viewHolder).mImageView);
            ((MyViewHolder) viewHolder).mType.setText(mList.get(i).getType());
            ((MyViewHolder) viewHolder).mWho.setText(mList.get(i).getWho());
        }
        //写入瀑布流的位置
        if (i != 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(((MyViewHolder) viewHolder).mImageView.getLayoutParams());
            params.setMargins(0, 100, 0, 0);
            ((MyViewHolder) viewHolder).mImageView.setLayoutParams(params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(((MyViewHolder) viewHolder).mImageView.getLayoutParams());
            params.setMargins(0, 50, 0, 0);
            ((MyViewHolder) viewHolder).mImageView.setLayoutParams(params);
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private onItenClicklistener mOnItemClikListener;

    //item点击的监听接口
    public interface onItenClicklistener {
        void onItemClik(String data);
    }

    //对外设置item点击暴露的方法
    public void setItemClikListener(onItenClicklistener mOnItemClikListener) {
        this.mOnItemClikListener = mOnItemClikListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView mImageView;
        private final TextView mType;
        private final TextView mWho;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mType = itemView.findViewById(R.id.type);
            mWho = itemView.findViewById(R.id.who);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClikListener.onItemClik(mList.get(getAdapterPosition()).getUrl());
                }
            });
        }
    }

    public void remove(int i) {
        mList.remove(0);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    public void add(int i) {
        mList.add(0, mList.get(i));
        notifyItemInserted(i);
    }
}
