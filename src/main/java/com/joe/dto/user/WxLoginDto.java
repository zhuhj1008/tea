package com.joe.dto.user;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 微信登录返回结果
 * create by Joe on 2018-06-22 15:30
 **/
@Data
public class WxLoginDto {

    private String sessionKey;

    private String openId;

    private String expiresIn;

    private String unionId;

}
