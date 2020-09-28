package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;

/**
 * @author WangYi
 * @description: 注册的契约类
 * @date :2020/5/20 20:39
 */
public interface IRegisterContract {
    interface IView extends IBaseView {
        void GetRegisterSuccess(RegisterBean bean);
        void GetRegisterFailure(String str);
        void GetEmailSuccess(EmailBean bean);
    }
    interface IPresenter{
        void GetRegister(String name,String pwd,String email,String code);
        void GetEmail(String email);
    }
    interface IModel{
        void GetRegister(String name,String pwd,String email,String code,ICallBack callBack);
        interface ICallBack{
            void GetRegisterSuccess(RegisterBean bean);
            void GetRegisterFailure(String str);
        }
        void GetEmail(String email,IModelICallBack callBack);
        interface IModelICallBack{
            void GetEmailSuccess(EmailBean bean);
        }
    }
}
