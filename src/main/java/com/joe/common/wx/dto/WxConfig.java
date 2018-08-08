package com.joe.common.wx.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信配置
 * create by Joe on 2018-08-08 17:06
 **/
@Component
@PropertySource("classpath:config/application.yml")
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    private String appId;

    private String secretKey;

    private String grantType;

    private String charSet;

    private String mchId;

    private String unifiedKey;

    private String grantUrl;

    private String unifiedOrderUrl;

    private String reFundUrl;

    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getUnifiedKey() {
        return unifiedKey;
    }

    public void setUnifiedKey(String unifiedKey) {
        this.unifiedKey = unifiedKey;
    }

    public String getGrantUrl() {
        return grantUrl;
    }

    public void setGrantUrl(String grantUrl) {
        this.grantUrl = grantUrl;
    }

    public String getUnifiedOrderUrl() {
        return unifiedOrderUrl;
    }

    public void setUnifiedOrderUrl(String unifiedOrderUrl) {
        this.unifiedOrderUrl = unifiedOrderUrl;
    }

    public String getReFundUrl() {
        return reFundUrl;
    }

    public void setReFundUrl(String reFundUrl) {
        this.reFundUrl = reFundUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
