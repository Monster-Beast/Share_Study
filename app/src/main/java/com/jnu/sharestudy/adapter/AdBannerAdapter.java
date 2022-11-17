package com.jnu.sharestudy.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.jnu.sharestudy.bean.BookBean;
import com.jnu.sharestudy.bean.Goods;
import com.jnu.sharestudy.fragment.AdBannerFragment;

import java.util.ArrayList;

public class AdBannerAdapter extends FragmentStateAdapter {
    private ArrayList<Goods> bb1;
        //传递数据
    public AdBannerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        bb1 =new ArrayList<>();
    }

    public AdBannerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

    }


    public void setData(ArrayList<Goods> bb1) {
        this.bb1 = bb1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args=new Bundle();
        if(bb1.size()>0){
            args.putSerializable("ad",bb1.get(position % bb1.size()));
        }

        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public int getSize() {
        return bb1 == null ? 0 : bb1.size();
    }



}

