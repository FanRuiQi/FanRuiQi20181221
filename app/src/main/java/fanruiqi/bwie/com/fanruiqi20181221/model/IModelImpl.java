package fanruiqi.bwie.com.fanruiqi20181221.model;

import com.google.gson.Gson;

import java.util.List;

import fanruiqi.bwie.com.fanruiqi20181221.MainActivity;
import fanruiqi.bwie.com.fanruiqi20181221.adapter.BannerAdapter;
import fanruiqi.bwie.com.fanruiqi20181221.bean.BannerBean;
import fanruiqi.bwie.com.fanruiqi20181221.callback.MyCallBack;
import fanruiqi.bwie.com.fanruiqi20181221.util.OkUtils;

public class IModelImpl implements IModel{
    @Override
    public void requestData(String url, final Class clazz, final MyCallBack myCallBack) {


        OkUtils.getInstance().doGet(url, new OkUtils.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String json) {

                Object o = new Gson().fromJson(json, clazz);
                myCallBack.setData(o);
            }
        });
    }
}
