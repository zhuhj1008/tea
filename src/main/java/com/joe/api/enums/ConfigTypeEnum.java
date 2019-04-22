package com.joe.api.enums;

/**
 * 系统配置枚举
 * create by Joe on 2018-08-07 15:23
 **/
public enum ConfigTypeEnum {

    WX_CONFIG(1),//微信商户参数配置
    WX_PAY_CONFIG(2),//微信支付参数配置
    WX_API_RUL_CONFIG(3),//微信api地址配置
    ;

    private Integer key;

    public Integer getKey() {
        return key;
    }

    ConfigTypeEnum(Integer key) {
        this.key = key;
    }


}
