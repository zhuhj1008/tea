package com.joe.dto.wx;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
@Data
@XmlRootElement(name = "xml")
public class WePayResult {


    /*业务结果  SUCCESS/FAIL*/
    @XmlElement(name = "result_code")
    private String resultCode;

    /*错误码*/
    @XmlElement(name = "err_code")
    private String errCode;

    /*错误描述*/
    @XmlElement(name = "err_code_des")
    private String errCodeDes;


    /*公众号appId*/
    @XmlElement(name = "appid")
    private String appId;

    /*商户号*/
    @XmlElement(name = "mch_id")
    private String mchId;

    /*设备号*/
    @XmlElement(name = "device_info")
    private String deviceInfo;

    /*随机字符串*/
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /*签名*/
    @XmlElement(name = "sign")
    private String sign;

    /*用户标识*/
    @XmlElement(name = "openid")
    private String openId;

    /*是否关注了公众号*/
    @XmlElement(name = "is_subscribe")
    private String isSubscribe;

    /*交易类型*/
    @XmlElement(name = "trade_type")
    private String tradeType;

    /*付款银行*/
    @XmlElement(name = "bank_type")
    private String bankType;

    /*付款总额 分*/
    @XmlElement(name = "total_fee")
    private int totalFee;

    /*应结金额 分*/
    @XmlElement(name = "settlement_total_fee")
    private int settlementTotalFee;

    /*货币种类 CNY*/
    @XmlElement(name = "fee_type")
    private String feeType;

    /*现金支付金额*/
    @XmlElement(name = "cash_fee")
    private String cashFee;

    /*货币类型 CNY*/
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;

    /*总代金券金额 CNY*/
    @XmlElement(name = "coupon_fee")
    private String couponFee;

    /*代金券使用数量 CNY*/
    @XmlElement(name = "coupon_count")
    private String couponCount;

    /*微信支付订单号*/
    @XmlElement(name = "transaction_id")
    private String transactionId;

    /*商户订单号*/
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /*商家数据包*/
    @XmlElement(name = "attach")
    private String attach;

    /*支付完成时间*/
    @XmlElement(name = "time_end")
    private String time_end;


}
