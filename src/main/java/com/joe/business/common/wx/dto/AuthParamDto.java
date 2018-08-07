package com.joe.business.common.wx.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 微信授权登录参数
 * create by Joe on 2018-08-07 16:56
 **/
public class AuthParamDto {

    /*小程序唯一标识*/
    private String appId;

    /*接口秘钥*/
    private String secret;

    /*用户请求code*/
    private String code;

    /*授权类型*/
    private String grantType;

    /*编码格式*/
    private String charSet;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
