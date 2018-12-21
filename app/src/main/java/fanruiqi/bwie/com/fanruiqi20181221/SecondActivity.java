package fanruiqi.bwie.com.fanruiqi20181221;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import fanruiqi.bwie.com.fanruiqi20181221.adapter.GridAdapter;
import fanruiqi.bwie.com.fanruiqi20181221.adapter.LineAdapter;
import fanruiqi.bwie.com.fanruiqi20181221.bean.GridBean;
import fanruiqi.bwie.com.fanruiqi20181221.bean.LineBean;
import fanruiqi.bwie.com.fanruiqi20181221.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.fanruiqi20181221.view.IView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,IView{
    private ImageView mImageView_qie,mImageView_qr;
    private RecyclerView mRecyclerView_grid;
    private RecyclerView mRecyclerView_line;
    IPrecenterImpl iPrecenter;
    private boolean p=true;
    private String url="http://www.zhaoapi.cn/product/getCatagory"; //网格布局接口
    private String Lineurl="http://www.zhaoapi.cn/product/getCarts?uid=71"; //线性布局接口

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        iPrecenter = new IPrecenterImpl(this);
        findViews();
        initData();
    }


    private void findViews() {
         mImageView_qie = findViewById(R.id.second_img_qie);
         mImageView_qr = findViewById(R.id.second_img_qr);
         mRecyclerView_grid = findViewById(R.id.second_rv);
         mRecyclerView_line = findViewById(R.id.second_rv2);

         mImageView_qie.setOnClickListener(this);
         mImageView_qr.setOnClickListener(this);
    }

    private void initData() {

        iPrecenter.requestData(url, GridBean.class);
        iPrecenter.requestData(Lineurl, LineBean.class);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.second_img_qie:  //切换布局

                if (p){
                    mRecyclerView_line.setLayoutManager(new GridLayoutManager(SecondActivity.this,2));

                }else {
                    mRecyclerView_line.setLayoutManager(new LinearLayoutManager(SecondActivity.this,LinearLayoutManager.VERTICAL,false));
                }

                p=!p;
                break;
            case R.id.second_img_qr:  //扫一扫

                checkPromisision();

                break;
        }
    }

    private void checkPromisision() {  //二维码调用方法

        startActivity(new Intent(SecondActivity.this,ScanActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==100 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(SecondActivity.this,ScanActivity.class));
        }

    }

    @Override
    public void showData(Object data) { //MVP返回的数据

        if (data instanceof GridBean){

            GridBean gridBean= (GridBean) data;

            List<GridBean.DataBean> data1 = gridBean.getData();
            GridAdapter gridAdapter = new GridAdapter(SecondActivity.this, data1);

            mRecyclerView_grid.setAdapter(gridAdapter);
            mRecyclerView_grid.setLayoutManager(new GridLayoutManager(SecondActivity.this,5));
        }else if (data instanceof LineBean){

            LineBean lineBean= (LineBean) data;

            List<LineBean.DataBean> data1 = lineBean.getData();
            LineAdapter lineAdapter = new LineAdapter(SecondActivity.this, data1);

            mRecyclerView_line.setAdapter(lineAdapter);
            mRecyclerView_line.addItemDecoration(new DividerItemDecoration(SecondActivity.this,DividerItemDecoration.VERTICAL));
            mRecyclerView_line.setLayoutManager(new LinearLayoutManager(SecondActivity.this,LinearLayoutManager.VERTICAL,false));

            lineAdapter.setOnItemClickListener(new LineAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String urls) {
                    Toast.makeText(SecondActivity.this,urls,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
