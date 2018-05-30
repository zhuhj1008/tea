package com.joe.bussiness.exception;

/**
 * 参数不合法异常
 * create by Joe on 2018-05-30 11:03
 **/
public class ParameterIllegalityException extends RuntimeException {


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParameterIllegalityException(String message) {
        this.message = message;
    }
}
