package com.joe.common.exception;

/**
 * 业务异常
 * create by Joe on 2018-07-10 10:42
 **/
public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
