package com.bw.movie.bean;

import java.util.List;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/2 21:43
 */
public class MyMovieBean {


    /**
     * result : [{"commentContent":"优质资讯推荐_腾讯网","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15256,"commentTime":1589770467000,"commentUserId":13973,"commentUserName":"李晏磊","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"汽车","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15255,"commentTime":1589770092000,"commentUserId":13976,"commentUserName":"yuanyuhan","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4},{"commentContent":"资讯,新闻,财经,房产,视频,NBA,科技,腾讯网,腾讯,QQ,Tencent","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15253,"commentTime":1589768735000,"commentUserId":13971,"commentUserName":"不知道","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15252,"commentTime":1589768651000,"commentUserId":13972,"commentUserName":"nide益达","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":8},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15251,"commentTime":1589768100000,"commentUserId":13977,"commentUserName":"yuanbo","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":5},{"commentContent":"首页","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":15248,"commentTime":1589650111000,"commentUserId":14001,"commentUserName":"sherry155","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"123456","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":10693,"commentTime":1588855486000,"commentUserId":13953,"commentUserName":"lcc","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":6},{"commentContent":"很精彩 十足的动作片","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":10674,"commentTime":1588840846000,"commentUserId":13874,"commentUserName":"我是你的骗子","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":1},{"commentContent":"�ܺÿ�","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-04-27/20200427210006.unknown","commentId":8486,"commentTime":1588503802000,"commentUserId":13795,"commentUserName":"魔王","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-05-10/20200510134259.temp.jpg","commentId":8481,"commentTime":1588298798000,"commentUserId":13946,"commentUserName":"可乐","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5}]
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
         * commentContent : 优质资讯推荐_腾讯网
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg
         * commentId : 15256
         * commentTime : 1589770467000
         * commentUserId : 13973
         * commentUserName : 李晏磊
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 4.5
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int isGreat;
        private int replyNum;
        private double score;
        private List<?> replyHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public List<?> getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(List<?> replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }
    }
}
