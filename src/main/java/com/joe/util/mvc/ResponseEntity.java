package com.joe.util.mvc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 接口返回信息
 * @author: joe.zhu
 * @since:2018-05-28 13:18:40
 * @version: 0.0.1
 */
public class ResponseEntity {

    /**
     * response code
     */
    public int code;

    /**
     * response message
     */
    public String message;

    /**
     * response data, if null, return DEFAULT_DATA;
     */
    public Object data;


    //success code
    private static final int SUCCESS_CODE = 1;

    //fail code
    private static final int FAIL_CODE = 0;

    private static final String DEFAULT_MESSAHE = "default_message";

    //default data, if data is null ,return this.
    private static final Object DEFAULT_DATA = "default_data";


    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public ResponseEntity(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity getSuccessEntity(String message, Object data) {
        if(StringUtils.isBlank(message)){
            message = DEFAULT_MESSAHE;
        }
        if(data == null){
            data = DEFAULT_DATA;
        }
        return new ResponseEntity(SUCCESS_CODE, message, data);
    }


    public static ResponseEntity getFailEntity(String message) {
        return new ResponseEntity(FAIL_CODE, message);
    }


    public static void main(String[] args) {
        ResponseEntity aa = getFailEntity("aa");
        System.out.println(JSON.toJSONString(aa));
    }

}
