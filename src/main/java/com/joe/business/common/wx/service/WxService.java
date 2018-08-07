package com.joe.business.common.wx.service;

import com.alibaba.fastjson.JSON;
import com.joe.business.common.wx.dto.UnifiedParamDto;
import com.joe.business.common.wx.dto.UnifiedSuccessDto;
import com.joe.business.common.wx.dto.WxConfig;
import com.joe.business.common.wx.enums.WxTradeTypeEnum;
import com.joe.business.common.wx.util.WxUtil;
import com.joe.business.common.wx.dto.WxApiUrl;
import com.joe.business.common.wx.util.AuthX509TrustManager;
import com.joe.util.JaxbUtil;
import com.joe.util.http.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxService.class);

    public static void main(String[] args) {

//        String openIdAndSessionKey = WxUtil.getOpenIdAndSessionKey("as");

        UnifiedParamDto unifiedParam = new UnifiedParamDto();
        unifiedParam.setAppId(WxConfig.APP_ID);
        unifiedParam.setMchId(WxConfig.BUSSINESS_CODE);
        unifiedParam.setNonceStr(WxUtil.CreateNonceString());
        unifiedParam.setBody("商品支付-促销商品");
        unifiedParam.setOutTradeNo("20");
        unifiedParam.setSpbillCreateIp("123.12.12.123");
        unifiedParam.setTotalFee(8880);
        unifiedParam.setDeviceInfo("WEB");
        unifiedParam.setSignType("MD5");
        unifiedParam.setNotifyUrl(WxApiUrl.NOTIFY_URL);
        unifiedParam.setTradeType(WxTradeTypeEnum.NATIVE.getType());
        wePayUnifiedOrder(unifiedParam);
    }

    /**
     * 获取用户微信唯一标识open id 和当前会话session key
     *
     * @param code 用户点击微信登录传过来的code
     * @return
     */
    public String getOpenIdAndSessionKey(String code) {

        logger.info("wxService-getOpenIdAndSessionKey, apply wx grant, code is {}.", code);

        Map<String, String> param = new HashedMap();
        param.put("appid", WxConfig.APP_ID);
        param.put("secret", WxConfig.SECRET);
        param.put("js_code", code);
        param.put("grant_type", WxConfig.GRANT_TYPE);
        String url = WxApiUrl.GRANT_URL;

        String result = HttpClientUtil.doPost(url, param, WxConfig.CHAR_SET);

        logger.info("wxService-getOpenIdAndSessionKey, apply wx grant, result is {}.", result);

        return result;
    }

    /**
     * 微信统一支付
     *
     * @param unifiedParam 统一支付参数Bean
     */
    public static UnifiedSuccessDto wePayUnifiedOrder(UnifiedParamDto unifiedParam) {

        logger.info("wxService-wePayUnifiedOrder, apply unified order, param is {}", unifiedParam);

        //todo 校验请求参数

        SortedMap<Object, Object> param = new TreeMap<>();

        //组装请求参数-必须参数
        param.put("appid", unifiedParam.getAppId());
        param.put("mch_id", unifiedParam.getMchId());
        param.put("nonce_str", unifiedParam.getNonceStr());
        param.put("body", unifiedParam.getBody());
        param.put("out_trade_no", unifiedParam.getOutTradeNo());
        param.put("total_fee", unifiedParam.getTotalFee() + "");
        param.put("spbill_create_ip", unifiedParam.getSpbillCreateIp());
        param.put("notify_url", unifiedParam.getNotifyUrl());
        param.put("trade_type", unifiedParam.getTradeType());

        //组装请求参数-非必须参数
        if (StringUtils.isNotBlank(unifiedParam.getDeviceInfo())) {
            param.put("device_info", unifiedParam.getDeviceInfo());
        }
        if (StringUtils.isNotBlank(unifiedParam.getSignType())) {
            param.put("sign_type", unifiedParam.getSignType());//0
        }
        if (StringUtils.isNotBlank(unifiedParam.getDetail())) {
            param.put("detail", unifiedParam.getDetail());//0
        }
        if (StringUtils.isNotBlank(unifiedParam.getAttach())) {
            param.put("attach", unifiedParam.getAttach());//0
        }
        if (StringUtils.isNotBlank(unifiedParam.getFeeType())) {
            param.put("fee_type", unifiedParam.getFeeType());
        }
        if (StringUtils.isNotBlank(unifiedParam.getTimeStart())) {
            param.put("time_start", unifiedParam.getTimeStart());
        }
        if (StringUtils.isNotBlank(unifiedParam.getTimeExpire())) {
            param.put("time_expire", unifiedParam.getTimeExpire());
        }
        if (StringUtils.isNotBlank(unifiedParam.getGoodsTag())) {
            param.put("goods_tag", unifiedParam.getGoodsTag());
        }
        if (StringUtils.isNotBlank(unifiedParam.getProductId())) {
            param.put("product_id", unifiedParam.getProductId());
        }
        if (StringUtils.isNotBlank(unifiedParam.getLimitPay())) {
            param.put("limit_pay", unifiedParam.getLimitPay());
        }
        if (StringUtils.isNotBlank(unifiedParam.getOpenId())) {
            param.put("openid", unifiedParam.getOpenId());
        }

        //获取签名
        String sign = WxUtil.getSign(param);
        param.put("sign", sign);

        //参数转XML
        String requestXml = WxUtil.getRequestXml(param);

        //请求API
        String result = AuthX509TrustManager.httpsRequest(WxApiUrl.UNIFIED_ORDER__API, "POST", requestXml);

        //解析响应体
        logger.info("wxService-wePayUnifiedOrder, apply unified order success, result is {}", result);
        return JaxbUtil.converyToJavaBean(result, UnifiedSuccessDto.class);
    }

}