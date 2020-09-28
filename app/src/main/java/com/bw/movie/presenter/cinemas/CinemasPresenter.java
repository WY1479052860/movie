package com.bw.movie.presenter.cinemas;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendCinemasBean;
import com.bw.movie.contract.cinemas.CinemasContract;
import com.bw.movie.model.cinemas.CinemaModel;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/28 21:48
 */
public class CinemasPresenter extends BasePresenter implements CinemasContract.IPresenter {

    private CinemaModel model;

    public CinemasPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new CinemaModel();
    }

    @Override
    public void GetRecommendMovie(int page, int count) {
       model.GetRecommendMovie(page, count, new CinemasContract.IModel.RecommendMovieCallBack() {
           @Override
           public void GetRecommendMovieSuccess(RecommendCinemasBean bean) {
               IBaseView view = getView();
               if(view instanceof CinemasContract.IView){
                   ((CinemasContract.IView) view).GetRecommendMovieSuccess(bean);
               }
           }
       });
    }

    @Override
    public void GetNearbySuccess(String longitude,String latitude, int page, int count) {
        model.GetNearbySuccess(longitude,latitude, page, count, new CinemasContract.IModel.NearbyCallBack() {
            @Override
            public void GetNearbySuccess(NearbyBean bean) {
                IBaseView view = getView();
                if(view instanceof CinemasContract.IView){
                    ((CinemasContract.IView) view).GetNearbySuccess(bean);
                }
            }
        });
    }
}
