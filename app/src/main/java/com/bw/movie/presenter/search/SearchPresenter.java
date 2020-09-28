package com.bw.movie.presenter.search;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ByKeywordBean;
import com.bw.movie.contract.search.ISearchContract;
import com.bw.movie.model.search.SearchModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/3 22:33
 */
public class SearchPresenter extends BasePresenter implements ISearchContract.IPresenter {

    private SearchModel model;

    public SearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new SearchModel();

    }

    @Override
    public void GetSearch(String keyword, int page, int count) {
        model.GetSearch(keyword, page, count, new ISearchContract.IModel.ICallBack() {
            @Override
            public void GetSearchSuccess(ByKeywordBean bean) {
                IBaseView view = getView();
                if(view instanceof ISearchContract.IView){
                    ((ISearchContract.IView) view).GetSearchSuccess(bean);
                }
            }

            @Override
            public void GetSearchFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ISearchContract.IView){
                    ((ISearchContract.IView) view).GetSearchFailure(str);
                }

            }
        });

    }
}
