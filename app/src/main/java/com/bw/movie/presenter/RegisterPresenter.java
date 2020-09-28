package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.IRegisterContract;
import com.bw.movie.model.RegisterModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/21 17:13
 */
public class RegisterPresenter extends BasePresenter implements IRegisterContract.IPresenter {

    private RegisterModel model;

    public RegisterPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new RegisterModel();

    }

    @Override
    public void GetRegister(String name, String pwd, String email, String code) {
        model.GetRegister(name, pwd, email, code, new IRegisterContract.IModel.ICallBack() {
            @Override
            public void GetRegisterSuccess(RegisterBean bean) {
                IBaseView view = getView();
                if(view instanceof IRegisterContract.IView){
                    ((IRegisterContract.IView) view).GetRegisterSuccess(bean);
                }
            }

            @Override
            public void GetRegisterFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IRegisterContract.IView){
                    ((IRegisterContract.IView) view).GetRegisterFailure(str);
                }
            }
        });

    }

    @Override
    public void GetEmail(String email) {
        model.GetEmail(email, new IRegisterContract.IModel.IModelICallBack() {
            @Override
            public void GetEmailSuccess(EmailBean bean) {
                IBaseView view = getView();
                if(view instanceof IRegisterContract.IView){
                    ((IRegisterContract.IView) view).GetEmailSuccess(bean);
                }
            }
        });
    }
}
