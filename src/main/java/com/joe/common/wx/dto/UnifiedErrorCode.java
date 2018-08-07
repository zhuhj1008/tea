package com.joe.common.wx.dto;

/**
 * 统一下单错误码
 * create by Joe on 2018-08-06 17:25
 **/
public enum UnifiedErrorCode {


    NOAUTH("NOAUTH", "商户无此接口权限"),
    NOTENOUGH("NOTENOUGH", "余额不足"),
    ORDERPAID("ORDERPAID", "商品订单已支付"),
    ORDERCLOSED("ORDERCLOSED", "订单已关闭"),
    SYSTEMERROR("SYSTEMERROR", "系统错误"),
    APPID_NOT_EXIST("APPID_NOT_EXIST", "APPID不存在"),
    MCHID_NOT_EXIST("MCHID_NOT_EXIST", "MCHID不存在"),
    APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH", "APPID和MCHID不匹配"),
    LACK_PARAMS("LACK_PARAMS", "缺少参数"),
    OUT_TRADE_NO_USED("OUT_TRADE_NO_USED", "商户订单号重复"),
    SIGNERROR("SIGNERROR", "签名错误"),
    XML_FORMAT_ERROR("XML_FORMAT_ERROR", "XML格式错误"),
    REQUIRE_POST_METHOD("REQUIRE_POST_METHOD", "请用POST方法"),
    POST_DATA_EMPTY("POST_DATA_EMPTY", "POST数据为空"),
    NOT_UTF8("NOT_UTF8", "编码格式错误");

    private String code;
    private String text;

    UnifiedErrorCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
