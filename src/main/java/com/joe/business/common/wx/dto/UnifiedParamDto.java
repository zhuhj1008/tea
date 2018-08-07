package com.joe.business.common.wx.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 统一支付请求参数
 * create by Joe on 2018-08-07 11:55
 **/
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
