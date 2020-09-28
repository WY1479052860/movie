package com.bw.movie.model;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.IRegisterContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/21 17:12
 */
public class RegisterModel implements IRegisterContract.IModel {
    @Override
    public void GetRegister(String name, String pwd, String email, String code, ICallBack callBack) {
        NetUtils.getInstance().getApis().getRegister(name,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean bean) {
                        if(callBack!=null){
                            callBack.GetRegisterSuccess(bean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(callBack!=null){
                            callBack.GetRegisterFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void GetEmail(String email, IModelICallBack callBack) {
        NetUtils.getInstance().getApis().getEmail(email)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmailBean bean) {
                        if(callBack!=null){
                            callBack.GetEmailSuccess(bean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
