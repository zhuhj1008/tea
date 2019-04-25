package com.joe.api.enums;

public enum UserTypeEnum {

    MANAGER(1, "管理员"),
    CUSTOMER_ORDINARY(2, "普通客户"),
    CUSTOMER_MEMBER(3, "会员客户");
    private int code;
    private String text;

    UserTypeEnum(int code, String text) {
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
