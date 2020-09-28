package com.bw.movie.contract.homecontract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.PopularBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.RenYingBean;
import com.bw.movie.bean.XbannerBean;

/**
 * @author WangYi
 * @description: xbanner契约类
 * @date :2020/5/22 21:20
 */
public interface IXbannerContract {
    interface IView extends IBaseView{
        void GetXBannerSuccess(XbannerBean bean);
        void GetXBannerFailure(String str);
        void GetReYingSuccess(RenYingBean bean);
        void GetReYingFailure(String str);
        void GetReleaseSuccess(ReleaseBean bean);
        void GetPopularSuccess(PopularBean bean);
    }
    interface IPresenter{
        void GetXBanner();
        void GetReYing(int page,int count);
        void GetRelease(int page,int count);
        void GetPopular(int page,int count);
    }
    interface IModel{
        void GetXBanner(ICallBack back);
        interface ICallBack{
            void GetXBannerSuccess(XbannerBean bean);
            void GetXBannerFailure(String str);
        }
        void GetReYing(int page,int count,IModelCallBack callBack);
        interface IModelCallBack{
            void GetReYingSuccess(RenYingBean bean);
            void GetReYingFailure(String str);
        }
        void GetRelease(int page,int count,IReleaseCallBack callBack);
        interface IReleaseCallBack{
            void GetReleaseSuccess(ReleaseBean bean);
        }
        void GetPopular(int page,int count,IPopularCallBack callBack);
        interface IPopularCallBack{
            void GetPopularSuccess(PopularBean bean);
        }
    }

}
