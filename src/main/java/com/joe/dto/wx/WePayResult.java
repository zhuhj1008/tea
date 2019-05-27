package com.joe.dto.wx;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WePayResult {

    /*请求结果  SUCCESS/FAIL*/
    @XmlElement(name = "return_code")
    private String returnCode;

    /*请求信息 */
    @XmlElement(name = "return_msg")
    private String returnMsg;

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

    /*货币种类 CNY*/
    @XmlElement(name = "fee_type")
    private String feeType;

    /*现金支付金额*/
    @XmlElement(name = "cash_fee")
    private String cashFee;

    /*微信支付订单号*/
    @XmlElement(name = "transaction_id")
    private String transactionId;

    /*商户订单号*/
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /*支付完成时间*/
    @XmlElement(name = "time_end")
    private String timeEnd;


}
