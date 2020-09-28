package com.bw.movie.bean;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/12 9:39
 */
public class UploadHeadPicBean {
    private String status;
    private String message;
    private String headPath;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
