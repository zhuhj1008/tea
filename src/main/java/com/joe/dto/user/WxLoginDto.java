package com.joe.dto.user;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 微信登录返回结果
 * create by Joe on 2018-06-22 15:30
 **/
public class WxLoginDto {

    private String sessionKey;

    private String openId;

    private String expiresIn;

    private String unionId;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
