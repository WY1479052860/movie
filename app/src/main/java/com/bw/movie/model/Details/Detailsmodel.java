package com.bw.movie.model.Details;

import android.widget.CheckBox;

import com.bw.movie.bean.DetailsBean;
import com.bw.movie.contract.details.DetailsContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/25 22:21
 */
public class Detailsmodel implements DetailsContract.IModel{


    @Override
    public void GetDetails(int movieId, ICallBack callBack) {
        NetUtils.getInstance().getApis().getDetails(movieId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        if(callBack!=null){
                            callBack.GetDetailsSuccess(detailsBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(callBack!=null){
                            callBack.GetDetailsFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
