package com.joe.api.enums;

public enum OrderDetailEnum {
    SHOP_CAR(1,"购物车"),PENDING(2,"已支付"),RECEIVE(4,"已签收"),EVALUATE(7,"已评价"),APPEND(8,"已追评");

    int value;
    String text;

    OrderDetailEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
