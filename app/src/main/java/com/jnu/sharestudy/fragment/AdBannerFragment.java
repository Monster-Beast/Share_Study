package com.jnu.sharestudy.fragment;

//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.jnu.sharestudy.bean.BookBean;
import com.jnu.sharestudy.bean.Goods;

public class AdBannerFragment extends Fragment {
    private ImageView iv;
    private Goods bb;//广告

    public static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment af= new AdBannerFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg=getArguments();
        bb=(Goods) arg.getSerializable("ad");
    }

    @Override
    public void onResume() {

        super.onResume();
        if(bb==null){
            Glide.with(getActivity()).load(bb.getBookPic()).into(iv);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        iv=new ImageView(getActivity());
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到图书详情
            }
        });



        return iv;
    }
}
