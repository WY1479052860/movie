package com.bw.movie.presenter.homepresenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.PopularBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.RenYingBean;
import com.bw.movie.bean.XbannerBean;
import com.bw.movie.contract.homecontract.IXbannerContract;
import com.bw.movie.model.homemodel.XbannerModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 21:22
 */
public class XbannerPresenter extends BasePresenter implements IXbannerContract.IPresenter {

    private XbannerModel model;

    public XbannerPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new XbannerModel();
    }

    @Override
    public void GetXBanner() {
        model.GetXBanner(new IXbannerContract.IModel.ICallBack() {
            @Override
            public void GetXBannerSuccess(XbannerBean bean) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetXBannerSuccess(bean);
                }
            }

            @Override
            public void GetXBannerFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetXBannerFailure(str);
                }
            }
        });

    }

    @Override
    public void GetReYing(int page,int count) {
        model.GetReYing(page,count,new IXbannerContract.IModel.IModelCallBack() {
            @Override
            public void GetReYingSuccess(RenYingBean bean) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetReYingSuccess(bean);
                }
            }

            @Override
            public void GetReYingFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetReYingFailure(str);
                }
            }
        });
    }

    @Override
    public void GetRelease(int page, int count) {
        model.GetRelease(page, count, new IXbannerContract.IModel.IReleaseCallBack() {
            @Override
            public void GetReleaseSuccess(ReleaseBean bean) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetReleaseSuccess(bean);
                }
            }
        });
    }

    @Override
    public void GetPopular(int page, int count) {
        model.GetPopular(page, count, new IXbannerContract.IModel.IPopularCallBack() {
            @Override
            public void GetPopularSuccess(PopularBean bean) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    ((IXbannerContract.IView) view).GetPopularSuccess(bean);
                }
            }
        });
    }
}
