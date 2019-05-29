package com.joe.api.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {

    NEW(1, "未支付"),
    PAY_SUCCESS(2, "已支付"),
    DELIVER(3, "已发货"),
    RECEIVE(4, "已签收"),

    CANCEL(10, "已取消"),
    PAY_FAIL(12, "支付失败");


    private int code;
    private String text;

    OrderStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static String getTextByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.code == code) {
                return orderStatusEnum.text;
            }
        }
        return null;
    }

}
