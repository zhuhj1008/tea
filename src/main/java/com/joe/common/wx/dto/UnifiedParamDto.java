package com.joe.common.wx.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 统一支付请求参数
 * create by Joe on 2018-08-07 11:55
 **/
@Data
public class UnifiedParamDto {

    /*小程序id 1*/
    private String appId;

    /*商户号 1*/
    private String mchId;

    /*设备号 0*/
    private String deviceInfo;

    /*随机字符串 1*/
    private String nonceStr;

    /*签名 1*/
    private String sign;

    /*签名类型 0*/
    private String signType;

    /*商品描述 1*/
    private String body;

    /*商品详情 0*/
    private String detail;

    /*附加数据 0*/
    private String attach;

    /*商户订单号 1*/
    private String outTradeNo;

    /*标价币种 0*/
    private String feeType;

    /*标价金额 1*/
    private Integer totalFee;

    /*终端IP 1*/
    private String spbillCreateIp;

    /*交易起始时间 0*/
    private String timeStart;

    /*交易结束时间 0*/
    private String timeExpire;

    /*订单优惠标记 0*/
    private String goodsTag;

    /*通知地址 1*/
    private String notifyUrl;
    /*交易类型 1*/
    private String tradeType;

    /*商品ID 0*/
    private String productId;

    /*指定支付方式 0*/
    private String limitPay;

    /*用户标识 0 trade_type=JSAPI，此参数必传*/
    private String openId;

}
