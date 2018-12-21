package fanruiqi.bwie.com.fanruiqi20181221.precenter;

import fanruiqi.bwie.com.fanruiqi20181221.callback.MyCallBack;
import fanruiqi.bwie.com.fanruiqi20181221.model.IModelImpl;
import fanruiqi.bwie.com.fanruiqi20181221.view.IView;

public class IPrecenterImpl implements IPrecenter{
    IView mIView;
    IModelImpl iModel;

    public IPrecenterImpl(IView IView) {
        mIView = IView;
        iModel = new IModelImpl();
    }

    @Override
    public void requestData(String url, Class clazz) {

        iModel.requestData(url, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                mIView.showData(data);
            }
        });
    }
}
