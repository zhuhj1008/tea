package com.joe.business.common.wx.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一下单成功返回属性
 * create by Joe on 2018-08-06 19:25
 **/
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


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
