package com.jnu.sharestudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jnu.sharestudy.R;

public class ViewPagerIndicator extends LinearLayout {
    private int mCount;//小圆点个数
    private int mIndex;//当前小圆点的位置
    private Context context;

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }


    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    public void setCurrentPostion(int currentIndex){
        mIndex=currentIndex;  //当前小圆点
        this.removeAllViews();//移除当前界面上存在的view
        int pex= context.getResources().getDimensionPixelSize(R.dimen.view_indacator_padding);
        for(int i=0;i<this.mCount;i++){

            ImageView imageView =new ImageView(context);
            if(mIndex==i){
                imageView.setImageResource(R.drawable.indicator_on);
            }else{
                imageView.setImageResource(R.drawable.indicator_off);

            }
            imageView.setPadding(pex,0,pex,0);
            this.addView(imageView);
        }


    }

    public  void  setCount(int count){
        this.mCount=count;
    }
}
