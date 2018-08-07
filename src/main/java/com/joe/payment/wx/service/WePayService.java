package com.joe.payment.wx.service;

import com.joe.payment.wx.dto.WxApiUrl;
import com.joe.payment.wx.dto.WxConfig;
import com.joe.payment.wx.dto.WxTradeTypeEnum;
import com.joe.payment.wx.util.AuthX509TrustManager;
import com.joe.payment.wx.util.WeiXinAuthUtil;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class WePayService {

    public static void main(String[] args) {
        applyWePay();
    }


    public static void applyWePay() {
        //组装参数
        SortedMap<Object, Object> param = new TreeMap<>();
        param.put("appid", WxConfig.APP_ID);
        param.put("mch_id", WxConfig.BUSSINESS_CODE);
        param.put("nonce_str", WeiXinAuthUtil.CreateNonceString());

        param.put("body", "商品支付");
        param.put("out_trade_no", "1234");
        param.put("total_fee", "888");
        param.put("spbill_create_ip", "123.12.12.123");
        param.put("notify_url", WxApiUrl.NOTIFY_URL);
        param.put("trade_type", WxTradeTypeEnum.APP.getType());
        //获取签名
        String sign = WeiXinAuthUtil.getSign(param);
        param.put("sign", sign);

        String requestXml = WeiXinAuthUtil.getRequestXml(param);

        //发送请求
        String post = AuthX509TrustManager.httpsRequest(WxApiUrl.UNIFIED_ORDER__API, "POST", requestXml);
        System.out.println(post);

    }


}
