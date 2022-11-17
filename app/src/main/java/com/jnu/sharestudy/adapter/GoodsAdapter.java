package com.jnu.sharestudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jnu.sharestudy.bean.Goods;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<Goods> goodsArrayList;

    public GoodsAdapter(Context context){
        this.mcontext=context;
    }

    public void setData(ArrayList<Goods> goodsArrayList){
        this.goodsArrayList=goodsArrayList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return goodsArrayList ==null? 0:goodsArrayList.size();
    }

    @Override
    public Goods getItem(int position) {
        return goodsArrayList==null? null:goodsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
