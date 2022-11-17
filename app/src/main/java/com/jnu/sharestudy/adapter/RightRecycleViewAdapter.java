package com.jnu.sharestudy.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jnu.sharestudy.R;
import com.jnu.sharestudy.bean.BookBean;
import com.jnu.sharestudy.bean.Goods;

import java.util.ArrayList;

public class RightRecycleViewAdapter extends RecyclerView.Adapter<RightRecycleViewAdapter.MyViewHolder>{
    private  Context mContext;
    private ArrayList<Goods> data;
    public RightRecycleViewAdapter(Context mContext){
        this.mContext=mContext;

    }public RightRecycleViewAdapter(Context mContext,ArrayList<Goods> goods){
        this.mContext=mContext;
        this.data=goods;

    }

    public void setData(ArrayList<Goods> goods){
        this.data=goods;

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RightRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null) {
            mContext = parent.getContext();
        }
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.meta_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightRecycleViewAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getBookPic()).into(holder.iv);

        holder.tv1.setText(data.get(position).getBookName());
        holder.tv1.setText(data.get(position).getAutherName());
        holder.tv1.setText(data.get(position).getPublisher());
        holder.tv1.setText(data.get(position).getPublishedDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv_pic);
            tv1=itemView.findViewById(R.id.tv_name);
            tv2=itemView.findViewById(R.id.tv_author);
            tv3=itemView.findViewById(R.id.tv_publisher);
            tv4=itemView.findViewById(R.id.tv_date);

        }
    }
}
