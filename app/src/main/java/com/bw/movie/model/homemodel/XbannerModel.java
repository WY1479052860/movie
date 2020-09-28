package com.bw.movie.model.homemodel;

import com.bw.movie.bean.PopularBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.RenYingBean;
import com.bw.movie.bean.XbannerBean;
import com.bw.movie.contract.homecontract.IXbannerContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 21:22
 */
public class XbannerModel implements IXbannerContract.IModel {
    @Override
    public void GetXBanner(ICallBack back) {
        NetUtils.getInstance().getApis().getXBanner()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XbannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XbannerBean xbannerBean) {
                        if(back!=null){
                            back.GetXBannerSuccess(xbannerBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(back!=null){
                            back.GetXBannerFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void GetReYing(int page,int count,IModelCallBack callBack) {
        NetUtils.getInstance().getApis().getReYing(page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RenYingBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RenYingBean bean) {
                if(callBack!=null){
                    callBack.GetReYingSuccess(bean);
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if(callBack!=null){
                    callBack.GetReYingFailure(e.getMessage());
                }

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void GetRelease(int page, int count, IReleaseCallBack callBack) {
        NetUtils.getInstance().getApis().getRelease(page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ReleaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ReleaseBean bean) {
                if(callBack!=null){
                    callBack.GetReleaseSuccess(bean);
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void GetPopular(int page, int count, IPopularCallBack callBack) {
        NetUtils.getInstance().getApis().getPopular(page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PopularBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PopularBean bean) {
                if(callBack!=null){
                    callBack.GetPopularSuccess(bean);
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
