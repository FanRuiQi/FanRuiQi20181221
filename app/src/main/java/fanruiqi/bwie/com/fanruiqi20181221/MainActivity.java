package fanruiqi.bwie.com.fanruiqi20181221;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

import fanruiqi.bwie.com.fanruiqi20181221.adapter.BannerAdapter;
import fanruiqi.bwie.com.fanruiqi20181221.bean.BannerBean;
import fanruiqi.bwie.com.fanruiqi20181221.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.fanruiqi20181221.util.OkUtils;
import fanruiqi.bwie.com.fanruiqi20181221.view.IView;

public class MainActivity extends AppCompatActivity implements IView{

    private ViewPager mViewPager;
    private Button mButton;
    IPrecenterImpl iPrecenter;
    private String url="http://www.zhaoapi.cn/ad/getAd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //轮播引导页
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPrecenter = new IPrecenterImpl(this);
        findViews();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }

    private void findViews() {  //获取控件
         mViewPager = findViewById(R.id.main_pager);
         mButton = findViewById(R.id.main_btn);

         iPrecenter.requestData(url,BannerBean.class);
        /*OkUtils.getInstance().doGet(url, new OkUtils.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String json) {

                BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
                List<BannerBean.DataBean> data = bannerBean.getData();
                BannerAdapter bannerAdapter = new BannerAdapter(MainActivity.this, data);
                mViewPager.setAdapter(bannerAdapter);
            }
        });*/

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { //设置滑动监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (mViewPager.getCurrentItem()==3){
                    mButton.setVisibility(View.VISIBLE);
                }else {
                    mButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void showData(Object data) {

        BannerBean bannerBean= (BannerBean) data;
        List<BannerBean.DataBean> data1 = bannerBean.getData();
        BannerAdapter bannerAdapter = new BannerAdapter(MainActivity.this, data1);
        mViewPager.setAdapter(bannerAdapter);
    }
}
