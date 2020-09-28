package com.bw.movie.bean;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/19 19:29
 */
public class LoginBean {
    /**
     * result : {"sessionId":"159041211310514015","userId":14015,"userInfo":{"email":"1479052860@qq.com","headPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","id":14015,"lastLoginTime":1590411683000,"nickName":"      小艺小艺","sex":1}}
     * message : 登陆成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * sessionId : 159041211310514015
         * userId : 14015
         * userInfo : {"email":"1479052860@qq.com","headPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","id":14015,"lastLoginTime":1590411683000,"nickName":"      小艺小艺","sex":1}
         */

        private String sessionId;
        private int userId;
        private UserInfoBean userInfo;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * email : 1479052860@qq.com
             * headPic : http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg
             * id : 14015
             * lastLoginTime : 1590411683000
             * nickName :       小艺小艺
             * sex : 1
             */

            private String email;
            private String headPic;
            private int id;
            private long lastLoginTime;
            private String nickName;
            private int sex;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
