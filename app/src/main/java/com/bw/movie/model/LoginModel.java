package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ILoginContract;
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
public class LoginModel implements ILoginContract.IModel {
    @Override
    public void GetLogin(String email, String pwd, ICallBack back) {
      NetUtils.getInstance().getApis().getLogin(email,pwd)
              .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<LoginBean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(LoginBean loginBean) {
                      if(back!=null){
                          back.GetLoginSuccess(loginBean);
                      }

                  }

                  @Override
                  public void onError(Throwable e) {
                      e.printStackTrace();
                      if(back!=null){
                          back.GetLoginFailure(e.getMessage());
                      }

                  }

                  @Override
                  public void onComplete() {

                  }
              });
    }
}
