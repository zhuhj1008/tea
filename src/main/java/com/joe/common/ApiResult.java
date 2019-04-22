package com.joe.common;

/**
 * @Description: 接口返回信息
 * @author: joe.zhu
 * @since:2018-05-28 13:18:40
 * @version: 0.0.1
 */
public class ApiResult {

    /**
     * 响应码 1=成功
     */
    public int code;

    /**
     * 响应报文
     */
    public String message;

    /**
     * 响应体
     */
    public Object data;


    //success code
    private static final int SUCCESS_CODE = 1;

    //fail code
    private static final int FAIL_CODE = 0;

    private static final String SUCCESS_MSG = "操作成功";

    private static final String FAIL_MSG = "操作失败";

    private ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResult getSuccessEntity(Object data) {
        return new ApiResult(SUCCESS_CODE, SUCCESS_MSG, data);
    }


    public static ApiResult getSuccessEntity(String message, Object data) {
        return new ApiResult(SUCCESS_CODE, message, data);
    }

    public static ApiResult getFailEntity() {
        return new ApiResult(FAIL_CODE, FAIL_MSG, null);
    }

    public static ApiResult getFailEntity(String message) {
        return new ApiResult(FAIL_CODE, message, null);
    }

}
