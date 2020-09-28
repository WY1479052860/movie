package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ILoginContract;
import com.bw.movie.model.LoginModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/21 17:13
 */
public class LoginPresenter extends BasePresenter implements ILoginContract.IPresenter {

    private LoginModel model;

    public LoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new LoginModel();
    }

    @Override
    public void GetLogin(String email, String pwd) {
        model.GetLogin(email, pwd, new ILoginContract.IModel.ICallBack() {
            @Override
            public void GetLoginSuccess(LoginBean bean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ((ILoginContract.IView) view).GetLoginSuccess(bean);
                }
            }

            @Override
            public void GetLoginFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ((ILoginContract.IView) view).GetLoginFailure(str);
                }
            }
        });

    }
}
