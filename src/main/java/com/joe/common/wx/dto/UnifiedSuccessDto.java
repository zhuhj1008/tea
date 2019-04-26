package com.joe.common.wx.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一下单成功返回属性
 * create by Joe on 2018-08-06 19:25
 **/
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnifiedSuccessDto {

    /*返回状态码*/
    @XmlElement(name = "return_code")
    private String return_code;

    /*返回信息*/
    @XmlElement(name = "return_msg")
    private String return_msg;

    //以下字段在return_code为SUCCESS的时候有返回
    /*应用Id*/
    @XmlElement(name = "appid")
    private String appid;

    /*商户号*/
    @XmlElement(name = "mch_id")
    private String mch_id;

    /*设备号*/
    @XmlElement(name = "device_info")
    private String device_info;

    /*随机字符串*/
    @XmlElement(name = "nonce_str")
    private String nonce_str;

    /*签名*/
    @XmlElement(name = "sign")
    private String sign;

    /*业务结果*/
    @XmlElement(name = "result_code")
    private String result_code;

    /*错误代码*/
    @XmlElement(name = "err_code")
    private String err_code;

    /*错误代码描述*/
    @XmlElement(name = "err_code_des")
    private String err_code_des;

    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /*交易类型*/
    @XmlElement(name = "trade_type")
    private String trade_type;

    /*预支付交易会话标识*/
    @XmlElement(name = "prepay_id")
    private String prepay_id;

}
