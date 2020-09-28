package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;

/**
 * @author WangYi
 * @description: 登录的契约类
 * @date :2020/5/20 20:40
 */
public interface ILoginContract {
    interface IView extends IBaseView{
        void GetLoginSuccess(LoginBean bean);
        void GetLoginFailure(String str);
    }
    interface IPresenter{
        void GetLogin(String email,String pwd);
    }
    interface IModel{
        void GetLogin(String email,String pwd,ICallBack back);
        interface ICallBack{
            void GetLoginSuccess(LoginBean bean);
            void GetLoginFailure(String str);
        }
    }
}
