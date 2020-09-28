package com.bw.movie.utils;

import com.bw.movie.bean.AreaBean;
import com.bw.movie.bean.ByKeywordBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.DiscussBean;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MyMovieBean;
import com.bw.movie.bean.NearBean;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.PopularBean;
import com.bw.movie.bean.QueryCinemaBean;
import com.bw.movie.bean.RecommendCinemasBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.RenYingBean;
import com.bw.movie.bean.ReserveBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.UploadHeadPicBean;
import com.bw.movie.bean.UserReserveBean;
import com.bw.movie.bean.WeChat;
import com.bw.movie.bean.XbannerBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author WangYi
 * @description: retrofit
 * @date :2020/5/19 19:11
 */
public interface Apis {
    //发送邮箱
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> getEmail(@Field("email")String email);
    //注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("nickName")String name, @Field("pwd")String pwd
                             , @Field("email")String email, @Field("code")String code);
    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("email")String email,@Field("pwd")String pwd);
    //XBanner
    @GET("tool/v2/banner")
    Observable<XbannerBean> getXBanner();
    //查询正在上映电影列表
    //http://mobile.bwstudent.com/movieApi/movie/v2/findReleaseMovieList
    @GET("movie/v2/findReleaseMovieList")
    Observable<RenYingBean> getReYing(@Query("page")int page,@Query("count")int count);
    //查询即将上映电影列表
    @GET("movie/v2/findComingSoonMovieList")
    Observable<ReleaseBean> getRelease(@Query("page")int page,@Query("count")int count);
    //查询热门电影列表
    @GET("movie/v2/findHotMovieList")
    Observable<PopularBean> getPopular(@Query("page")int page,@Query("count")int count);
    //电影详情列表
    //http://mobile.bwstudent.com/movieApi/movie/v2/findMoviesDetail
    @GET("movie/v2/findMoviesDetail")
    Observable<DetailsBean> getDetails(@Query("movieId")int movieId);
    //查询推荐影院信息
    //http://mobile.bwstudent.com/movieApi/cinema/v1/findRecommendCinemas
    @GET("cinema/v1/findRecommendCinemas")
    Observable<RecommendCinemasBean> getCinemas(@Query("page")int page,@Query("count")int count);
    //查询附近影院
    //http://mobile.bwstudent.com/movieApi/cinema/v1/findNearbyCinemas
    @GET("cinema/v1/findNearbyCinemas")
    Observable<NearbyBean> getNearby(@Query("longitude")String longitude,@Query("latitude")String latitude ,@Query("page")int page,@Query("count")int count );
     //查询区域列表
    //http://mobile.bwstudent.com/movieApi/tool/v2/findRegionList
    @GET("tool/v2/findRegionList")
    Observable<AreaBean> getArea();
    //根据区域查询影院
    //http://mobile.bwstudent.com/movieApi/cinema/v2/findCinemaByRegion
    @GET("cinema/v2/findCinemaByRegion")
    Observable<NearBean> getNear(@Query("regionId")int regionId);
    //电影评论
    //http://mobile.bwstudent.com/movieApi/movie/v2/findAllMovieComment?movieId=1&page=1&count=10
    @GET("movie/v2/findAllMovieComment")
    Observable<MyMovieBean> getMyMovie(@Query("movieId")int movieId,@Query("page")int page, @Query("count") int count);
    //根据关键字查询电影信息
    //http://mobile.bwstudent.com/movieApi/movie/v2/findMovieByKeyword
    @GET("movie/v2/findMovieByKeyword")
    Observable<ByKeywordBean> getSearch(@Query("keyword")String keyword,@Query("page") int page,@Query("count") int count);
    //查询电影信息明细
    //http://mobile.bwstudent.com/movieApi/cinema/v1/findCinemaInfo?cinemaId=1
    @GET("cinema/v1/findCinemaInfo")
    Observable<CinemaBean> getCinemaInfo(@Query("cinemaId") int cinemaId);
    //查询电影评论
    //http://mobile.bwstudent.com/movieApi/cinema/v1/findAllCinemaComment?cinemaId=1&page=1&count=10
    @GET("cinema/v1/findAllCinemaComment")
    Observable<CinemaCommentBean> getCinemaComment(@Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);
    //预约
    //http://mobile.bwstudent.com/movieApi/movie/v2/verify/reserve
    @POST("movie/v2/verify/reserve")
    @FormUrlEncoded
    Observable<ReserveBean> getReserve(@Field("movieId") int movieId);
     //添加用户对影片的评论
    //http://mobile.bwstudent.com/movieApi/movie/v1/verify/movieComment
    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<DiscussBean> getmovieComment(@Field("movieId")int movieId,@Field("commentContent")String commentContent, @Field("score") double score);
    // 查询用户预约电影信息
    //http://mobile.bwstudent.com/movieApi/user/v2/verify/findUserReserve
    @GET("user/v2/verify/findUserReserve")
    Observable<UserReserveBean> getUserReserve();

    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<QueryCinemaBean> getQueryBean(@Query("movieId")int movieId, @Query("regionId")int regionId, @Query("page")int page, @Query("count")int count);
    //头像上传
    @POST("user/v1/verify/uploadHeadPic")
    Observable<UploadHeadPicBean> getUpLoadHeadPicBean(@Body RequestBody body);
    //微信登录
    //http://172.17.8.100/movieApi/user/v1/weChatBindingLogin
    @POST("user/v1/weChatBindingLogin")
    @FormUrlEncoded
    Observable<WeChat>getWeChat(@Field("code")String code);
    //http://mobile.bwstudent.com/movieApi/cinema/v2/findCinemaScheduleList
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<ScheduleBean> getSchedule(@Query("cinemaId")int cinemaId,@Query("page")int page,@Query("count")int count);

}

