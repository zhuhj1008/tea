package com.joe.api.enums;

public enum OrderDetailEnum {

    SHOP_CAR(1, "购物车"),
    PENDING(2, "已支付"),
    RECEIVE(4, "已签收"),
    EVALUATE(7, "已评价"),
    APPEND(8, "已追评");

    private int code;
    private String text;

    OrderDetailEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
