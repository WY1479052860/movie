package com.bw.movie.model.cinemas;


import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendCinemasBean;
import com.bw.movie.contract.cinemas.CinemasContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description: 契约类
 * @date :2020/5/28 21:37
 */
public class CinemaModel implements CinemasContract.IModel {

    @Override
    public void GetRecommendMovie(int page, int count, RecommendMovieCallBack callBack) {
        NetUtils.getInstance().getApis().getCinemas(page,count)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendCinemasBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendCinemasBean bean) {
                        if(callBack!=null){
                            callBack.GetRecommendMovieSuccess(bean);
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
    public void GetNearbySuccess(String longitude,String latitude,int page, int count, NearbyCallBack callBack) {
       NetUtils.getInstance().getApis().getNearby(longitude,latitude,page,count)
               .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<NearbyBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(NearbyBean bean) {
                       if(callBack!=null){
                           callBack.GetNearbySuccess(bean);
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
