package com.joe.manager.util.mvc;

/**
 * @className: ReturnClientEntity
 * @Description: 网站WEB返回前端Ajax 传输类
 * @author: <a href="mailto:tony.song@tianyitechs.com">tony.song</a>
 * @since: songjian 2017-03-29 15:40:10
 * @version: 0.0.1
 */
public class ResultClientEntity {

    public  Boolean code;
    public String message;
    public  Object data;

    public ResultClientEntity(Boolean code) {
        this.code = code;
    }

    public ResultClientEntity(Boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultClientEntity(Boolean code, Object data) {
        this.code = code;
        this.data = data;
    }


    public static ResultClientEntity getSuccessEntity(){
        return new ResultClientEntity(true);
    }

    public static ResultClientEntity getSuccessEntity(Object data){
        return new ResultClientEntity(true,data);
    }

    public static ResultClientEntity getFailEntity(Object data){
        return new ResultClientEntity(false,data);
    }

    public static ResultClientEntity getFailEntity(String message){
        return new ResultClientEntity(false,message);
    }

}
