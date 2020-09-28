package com.bw.movie.model;

import com.bw.movie.bean.WeChat;
import com.bw.movie.contract.IWexinContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/15 11:28
 */
public class WeXinModel implements IWexinContract.IModel {
    @Override
    public void WeChatSuccess(String code, WeChatCoallack mWeChatCoallack) {
        NetUtils.getInstance().getApis().getWeChat(code)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChat>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChat weChat) {
                        if(mWeChatCoallack!=null){
                            mWeChatCoallack.OnWeChatSuccess(weChat);
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
