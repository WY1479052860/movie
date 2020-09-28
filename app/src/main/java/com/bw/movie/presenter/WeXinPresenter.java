package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.WeChat;
import com.bw.movie.contract.IWexinContract;
import com.bw.movie.model.WeXinModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/15 11:30
 */
public class WeXinPresenter extends BasePresenter implements IWexinContract.IPreantert{

    private WeXinModel model;

    public WeXinPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model =  new WeXinModel();

    }

    @Override
    public void WeChatSuccess(String code) {
        model.WeChatSuccess(code, new IWexinContract.IModel.WeChatCoallack() {
            @Override
            public void OnWeChatSuccess(WeChat weChat) {
                IBaseView view = getView();
                if(view instanceof IWexinContract.IView){
                    ((IWexinContract.IView) view).OnWeChatSuccess(weChat);
                }
            }
        });

    }
}
