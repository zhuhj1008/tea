package com.joe.api.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {
    NEW(1,"新订单"),
    PAYMENT(2,"已支付"),
    DELIVER(3,"已发货"),
    RECEIVE(4,"已签收"),
    CANCEL(10,"已取消");

    int value;
    String text;

    OrderStatusEnum(int value, String text) {
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
