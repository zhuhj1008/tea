package com.joe.util.mvc;

/**
 * 请求体
 * create by Joe on 2018-05-27 14:45
 **/
public class RequestEntity {

    private String timeStr;
    private String signature;
    private String body;

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
