package com.bw.movie.contract.details;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.LoginBean;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/25 22:21
 */
public interface DetailsContract {
    interface IView extends IBaseView {
        void GetDetailsSuccess(DetailsBean bean);
        void GetDetailsFailure(String str);
    }
    interface IPresenter{
        void GetDetails(int movieId);
    }
    interface IModel{
        void GetDetails(int movieId,ICallBack callBack);
        interface ICallBack{
            void GetDetailsSuccess(DetailsBean bean);
            void GetDetailsFailure(String str);
        }
    }
}
