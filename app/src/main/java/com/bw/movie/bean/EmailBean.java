package com.bw.movie.bean;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/21 21:32
 */
public class EmailBean {

    /**
     * message : 发送成功
     * status : 0000
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
