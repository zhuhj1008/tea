package com.joe.business.common.wx.enums;

public enum WxTradeTypeEnum {

    /*微信交易类型:公众号支付 小程序支付*/
    JSAPI("JSAPI"),

    /*微信交易类型:原生扫码支付*/
    NATIVE("NATIVE"),

    /*微信甲乙类型:APP支付*/
    APP("APP");

    String type;

    public String getType() {
        return type;
    }

    WxTradeTypeEnum(String type) {
        this.type = type;
    }
}
