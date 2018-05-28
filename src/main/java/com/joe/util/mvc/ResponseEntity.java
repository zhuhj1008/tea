package com.joe.util.mvc;

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

    //default data, if data is null ,return this.
    private static final Object DEFAULT_DATA = "default_data";


    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = DEFAULT_DATA;
    }

    public ResponseEntity(int code, Object data) {

        if (data == null) {
            this.data = DEFAULT_DATA;
        }

        this.code = code;
        this.data = data;
    }


    public static ResponseEntity getSuccessEntity(Object data) {
        return new ResponseEntity(SUCCESS_CODE, data);
    }

    public static ResponseEntity getFailEntity(Object data) {
        return new ResponseEntity(FAIL_CODE, data);
    }

    public static ResponseEntity getFailEntity(String message) {
        return new ResponseEntity(FAIL_CODE, message);
    }

}
