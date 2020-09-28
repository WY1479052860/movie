package com.bw.movie.contract.search;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ByKeywordBean;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/3 22:30
 */
public interface ISearchContract {
    interface IView extends IBaseView{
        void GetSearchSuccess(ByKeywordBean bean);
        void GetSearchFailure(String str);
    }
    interface IPresenter{
        void GetSearch(String keyword,int page,int count);
    }
    interface IModel{
        void GetSearch(String keyword,int page,int count,ICallBack callBack);
        interface ICallBack{
            void GetSearchSuccess(ByKeywordBean bean);
            void GetSearchFailure(String str);
        }
    }
}
