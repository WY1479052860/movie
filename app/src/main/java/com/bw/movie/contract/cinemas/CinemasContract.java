package com.bw.movie.contract.cinemas;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendCinemasBean;

/**
 * @author WangYi
 * @description: 契约类
 * @date :2020/5/28 21:37
 */
public interface CinemasContract {
    interface IView extends IBaseView{
        void GetRecommendMovieSuccess(RecommendCinemasBean bean);
        void GetNearbySuccess(NearbyBean bean);
    }
    interface IPresenter{
        void GetRecommendMovie(int page,int count);
        void GetNearbySuccess(String longitude,String latitude, int page,int count);
    }
    interface IModel{
        void GetRecommendMovie(int page,int count,RecommendMovieCallBack callBack);
        interface RecommendMovieCallBack{
            void GetRecommendMovieSuccess(RecommendCinemasBean bean);
        }
        void GetNearbySuccess(String longitude,String latitude,int page,int count,NearbyCallBack callBack);

        interface NearbyCallBack{
            void GetNearbySuccess(NearbyBean bean);
        }

    }
}
