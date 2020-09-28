package com.bw.movie.presenter.details;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.contract.details.DetailsContract;
import com.bw.movie.model.Details.Detailsmodel;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/25 22:51
 */
public class DetailsPresenter extends BasePresenter implements DetailsContract.IPresenter {

    private Detailsmodel detailsmodel;

    public DetailsPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        detailsmodel = new Detailsmodel();
    }

    @Override
    public void GetDetails(int movieId) {
        detailsmodel.GetDetails(movieId, new DetailsContract.IModel.ICallBack() {
            @Override
            public void GetDetailsSuccess(DetailsBean bean) {
                IBaseView view = getView();
                if(view instanceof DetailsContract.IView){
                    ((DetailsContract.IView) view).GetDetailsSuccess(bean);
                }
            }

            @Override
            public void GetDetailsFailure(String str) {
                IBaseView view = getView();
                if(view instanceof DetailsContract.IView){
                    ((DetailsContract.IView) view).GetDetailsFailure(str);
                }
            }
        });

    }
}
