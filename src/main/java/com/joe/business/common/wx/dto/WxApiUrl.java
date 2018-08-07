package com.joe.business.common.wx.dto;

public class WxApiUrl {

    /*用户授权地址地址*/
    public static final String GRANT_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /*预支付地址*/
    public static final String UNIFIED_ORDER__API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /*微信申请退款url*/
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /*微信支付通知url*/
    public static final String NOTIFY_URL = "www.qiaoyuntea.xyz/order/wxResend";
}
