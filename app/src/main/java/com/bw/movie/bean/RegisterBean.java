package com.bw.movie.bean;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/19 19:29
 */
public class RegisterBean {

    /**
     * message : 密码不能为空
     * status : 1002
     */

    private String message;
    private String status;

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
}
