package com.joe.api.enums;

public enum UserTypeEnum {

    MANAGER(1, "管理员"),
    CUSTOMER_ORDINARY(2, "普通客户"),
    CUSTOMER_MEMBER(3, "会员客户");
    int value;
    String text;

    UserTypeEnum(int value, String text) {
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
