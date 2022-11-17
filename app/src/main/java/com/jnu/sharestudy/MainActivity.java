package com.jnu.sharestudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.jnu.sharestudy.adapter.AdBannerAdapter;
import com.jnu.sharestudy.adapter.MyFragmentPagerAdpter;
import com.jnu.sharestudy.adapter.RightRecycleViewAdapter;
import com.jnu.sharestudy.bean.Goods;
import com.jnu.sharestudy.fragment.BlankFragment;
import com.jnu.sharestudy.fragment.RightFragment;
import com.jnu.sharestudy.utils.Constant;
import com.jnu.sharestudy.utils.JsonParse;
import com.jnu.sharestudy.view.FindRecyclerView;
import com.jnu.sharestudy.view.ViewPagerIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager2 viewPager;
    private DrawerLayout drawerLayout;//滑动菜单
    private LinearLayout linearLayout1,linearLayout2;
    private ImageView imageView1,imageView2,imageViewnow;
    private Button button_drawer,button_search,button_add;
    ArrayList<Fragment> fragments;
    ArrayList<Goods> Goods;

    private RightFragment right_fragment;
    private RightRecycleViewAdapter rightRecycleViewAdapter;
    private FindRecyclerView findRecyclerView;
    private ViewPager2 adPager;  //广告
    private ViewPagerIndicator vpi; //小圆点
    private View adBannerLay;  //广告条容器
    private AdBannerAdapter ada; //广告数据适配器（fragment）
    public static  final int MSG_AD_SLID =1; //广告自动滑动
    public static  final int MSG_BOOK_OK =2;  //获取数据

    private MHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler =new MHandler();
        initData();
        initPageir();
        initTabView();
        initTittleBar();
        initDrawer();
    }
    private void initDrawer(){
        drawerLayout=findViewById(R.id.drawer_layout);
    }
    private void initTabView() {
        linearLayout1=findViewById(R.id.lay1);
        linearLayout1.setOnClickListener(this);
        linearLayout2=findViewById(R.id.lay2);
        linearLayout2.setOnClickListener(this);
        imageView1=findViewById(R.id.iv1);
        imageView2=findViewById(R.id.iv2);
        imageView1.setSelected(true);
        imageViewnow=imageView1;
    }
    private void initTittleBar(){
        button_drawer=findViewById(R.id.bt_go_drawer);

        button_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        button_search=findViewById(R.id.bt_search);
        button_add=findViewById(R.id.bt_add);
    }
    private void resetSize(){

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width3 = dm.widthPixels;
        int adLheight=width3/3;//广告条高度
        ViewGroup.LayoutParams adlp= adBannerLay.getLayoutParams();
        adlp.width=width3;
        adlp.height=adLheight;
        adBannerLay.setLayoutParams(adlp);


    }
    private void initRight(){
        right_fragment=new RightFragment().getInstance(Goods);
        //right_fragment=findViewById(R.id.right_fragment);
        findRecyclerView=findViewById(R.id.find_rl);
        rightRecycleViewAdapter=new RightRecycleViewAdapter(this);
        adBannerLay=findViewById(R.id.adbanner_layout);
        vpi=findViewById(R.id.advert_indicator);
        adPager=findViewById(R.id.slidingAdverBanner);


        adPager.setLongClickable(false);
        ada=new AdBannerAdapter(getSupportFragmentManager(),getLifecycle());
        adPager.setAdapter(ada);
        adPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(ada.getSize()>0){
                    vpi.setCurrentPostion(position % ada.getSize());//设置小圆点
                }
            }

        });
        resetSize();
        new AdAutoSlidThread().start();

    }
    class AdAutoSlidThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    sleep(5000);//睡眠5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(mHandler !=null){
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }


            }
        }
    }

    private void initData(){
        Goods=new ArrayList<Goods>();
        try {
            //打开存放在assets文件夹下面的json格式的文件并且放在文件输入流里面
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("Goods.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();


            //新建一个json对象，用它对数据进行操作
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            //单独去一个值

            JSONArray jsonArray = jsonObject.getJSONArray("Goods");
            //取一个数组值
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Goods goods=new Goods();
                goods.setId(object.getInt("id"));
                goods.setBanner(object.getString("banner"));
                goods.setBookName(object.getString("bookName"));
                goods.setAutherName(object.getString("autherName"));
                goods.setPublisher(object.getString("publisher"));
                goods.setPublishedDate(object.getString("publishedDate"));
                goods.setBookPic(object.getString("bookPic"));
                Goods.add(goods);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        OkHttpClient okHttpClient =new OkHttpClient();
//        Request request =new Request.Builder().url(Constant.WEB_SITE+Constant.REQUEST_SHOP_URL).build();
//        Call call=okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String res=response.body().string();//获取book数据
//                Message msg=new Message();
//                msg.what=MSG_BOOK_OK;
//                msg.obj=res;
//                mHandler.sendMessage(msg);
//            }
//        });
    }
    class MHandler extends Handler{

        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);

            switch (msg.what){
                case MSG_BOOK_OK:
                    if(msg.obj!=null){
                        String vlResult=(String) msg.obj;
                        ArrayList<Goods> goods= JsonParse.getInstance().getGoods(vlResult);
                        rightRecycleViewAdapter.setData(goods);
                        if(goods!=null){
                            if(goods.size()>0){
                                ada.setData(goods);
                                vpi.setCount(goods.size());
                                vpi.setCurrentPostion(0);

                            }
                        }
                    }
                    break;
                case MSG_AD_SLID:
                    if(ada.getItemCount()>0){
                        adPager.setCurrentItem(adPager.getCurrentItem()+1);

                    }
                    break;
            }
        }
    }
    private  void initPageir(){
        viewPager=findViewById(R.id.viewPager);
        fragments=new ArrayList<>();
        initRight();

        fragments.add(BlankFragment.newInstance("第一栏"));
        fragments.add(BlankFragment.newInstance("第er栏"));
        fragments.add(new RightFragment().getInstance(Goods));
        MyFragmentPagerAdpter pagerAdpter=new MyFragmentPagerAdpter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdpter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changTab(position);
            }



            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
    private void changTab(int position) {
        imageViewnow.setSelected(false);
        Log.d("monster",imageViewnow.toString());
        switch (position){
            case R.id.lay1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.lay2:
                viewPager.setCurrentItem(1);
                break;

            case 0:

                imageView1.setSelected(true);
                imageViewnow=imageView1;
                break;
            case 1:
                imageView2.setSelected(true);
                imageViewnow=imageView2;
                break;




        }
    }

    @Override
    public void onClick(View view) {
        changTab(view.getId());

    }
}