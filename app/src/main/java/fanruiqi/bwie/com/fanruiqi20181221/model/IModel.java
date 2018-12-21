package fanruiqi.bwie.com.fanruiqi20181221.model;

import fanruiqi.bwie.com.fanruiqi20181221.callback.MyCallBack;

public interface IModel {
    void requestData(String url, Class clazz, MyCallBack myCallBack);
}
