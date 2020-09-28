package com.bw.movie.bean;

import java.util.List;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/19 14:44
 */
public class ScheduleBean {

    /**
     * result : [{"director":"\r\n刘伟强","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"name":"中国机长","score":9.4,"starring":"张涵予,欧豪,袁泉,张天爱,李沁","trailerUrl":"http://mobile.bwstudent.com/video/movie/zgjz/zgjz1.mp4"},{"director":"陈凯歌","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/whwdzg/whwdzg1.jpg","movieId":23,"name":"我和我的祖国","score":9.7,"starring":"黄渤,张译,杜江,葛优,刘昊然,吴京","trailerUrl":"http://mobile.bwstudent.com/video/movie/whwdzg/whwdzg1.mp4"},{"director":"罗森·马歇尔·瑟伯","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","score":8.5,"starring":"道恩·强森,昆凌,文峰,黄经汉","trailerUrl":"http://mobile.bwstudent.com/video/movie/mtyj/mtyj1.ts"},{"director":"\r\n李仁港","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/pdz/pdz1.jpg","movieId":25,"name":"攀登者","score":9.4,"starring":"吴京,章子怡,井柏然,胡歌","trailerUrl":"http://mobile.bwstudent.com/video/movie/pdz/pdz1.mp4"},{"director":"贾樟柯","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/jhen/jhen1.jpg","movieId":19,"name":"江湖儿女","score":9.7,"starring":"赵涛,廖凡,徐峥,梁嘉艳","trailerUrl":"http://mobile.bwstudent.com/video/movie/jhen/jhen1.mp4"},{"director":"钱嘉乐","imageUrl":"http://mobile.bwstudent.com/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"name":"黄金兄弟","score":9.3,"starring":"郑伊健,陈小春,谢天华,林晓峰","trailerUrl":"http://mobile.bwstudent.com/video/movie/hjxd/hjxd1.mp4"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * director :
         刘伟强
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/zgjz/zgjz1.jpg
         * movieId : 24
         * name : 中国机长
         * score : 9.4
         * starring : 张涵予,欧豪,袁泉,张天爱,李沁
         * trailerUrl : http://mobile.bwstudent.com/video/movie/zgjz/zgjz1.mp4
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;
        private String trailerUrl;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }

        public String getTrailerUrl() {
            return trailerUrl;
        }

        public void setTrailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
        }
    }
}
