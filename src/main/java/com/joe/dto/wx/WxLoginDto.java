package com.joe.dto.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 微信登录返回结果
 * create by Joe on 2018-06-22 15:30
 **/
@Data
public class WxLoginDto {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;

}
