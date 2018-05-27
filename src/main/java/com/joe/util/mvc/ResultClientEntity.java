package com.joe.util.mvc;

/**
 * @className: ReturnClientEntity
 * @Description: 网站WEB返回前端Ajax 传输类
 * @author: <a href="mailto:tony.song@tianyitechs.com">tony.song</a>
 * @since: songjian 2017-03-29 15:40:10
 * @version: 0.0.1
 */
public class ResultClientEntity {

    public int code;
    public String message;
    public Object data;

    private static final int SUCCESS_CODE = 1;
    private static final int FAIL_CODE = 0;


    public ResultClientEntity(int code) {
        this.code = code;
    }


    public ResultClientEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultClientEntity(int code, Object data) {
        this.code = code;
        this.data = data;
    }


    public static ResultClientEntity getSuccessEntity(){
        return new ResultClientEntity(SUCCESS_CODE);
    }

    public static ResultClientEntity getSuccessEntity(Object data){
        return new ResultClientEntity(SUCCESS_CODE,data);
    }

    public static ResultClientEntity getFailEntity(Object data){
        return new ResultClientEntity(FAIL_CODE,data);
    }

    public static ResultClientEntity getFailEntity(String message){
        return new ResultClientEntity(FAIL_CODE,message);
    }

}
