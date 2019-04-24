package com.joe.api.enums;

public enum PictureTypeEnum {
    COMMODITY_DETAIL(1,"商品细节图"),
    COMMODITY_BANNER(2,"商品轮播图");

    private Integer code;

    private String text;

    PictureTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
