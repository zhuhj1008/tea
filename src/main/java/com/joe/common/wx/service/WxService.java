package com.joe.common.wx.service;

import com.joe.common.exception.BusinessException;
import com.joe.service.RedisService;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.common.wx.dto.UnifiedSuccessDto;
import com.joe.common.wx.dto.WxConfig;
import com.joe.common.wx.enums.WxTradeTypeEnum;
import com.joe.common.wx.util.AuthX509TrustManager;
import com.joe.common.wx.util.WxUtil;
import com.joe.util.JaxbUtil;
import com.joe.util.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxService.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private WxConfig wxConfig;


    /**
     * 获取用户微信唯一标识open id 和当前会话session key
     *
     * @param code 用户点击微信登录传过来的code
     * @return
     */
    public String getOpenIdAndSessionKey(String code) {

        logger.info("wxService-getOpenIdAndSessionKey, apply wx grant, code is {}.", code);

        Object url = redisService.getCache(wxConfig.getGrantUrl());
        Object appId = redisService.getCache(wxConfig.getAppId());
        Object secretKey = redisService.getCache(wxConfig.getSecretKey());
        Object grantType = redisService.getCache(wxConfig.getGrantType());
        Object charSet = redisService.getCache(wxConfig.getCharSet());

        if (url == null || appId == null || secretKey == null || grantType == null || charSet == null) {
            throw new BusinessException("wx grant ,param error.");
        }

        SortedMap<String, String> param = new TreeMap<>();
        param.put("appid", appId.toString());
        param.put("secret", secretKey.toString());
        param.put("js_code", code);
        param.put("grant_type", grantType.toString());

        String result = HttpClientUtil.doPost(url.toString(), param, charSet.toString());

        logger.info("wxService-getOpenIdAndSessionKey, apply wx grant, result is {}.", result);

        return result;
    }

    /**
     * 微信统一支付
     *
     * @param unifiedParam 统一支付参数Bean
     */
    public UnifiedSuccessDto wePayUnifiedOrder(UnifiedParamDto unifiedParam) {

        logger.info("wxService-wePayUnifiedOrder, apply unified order, param is {}", unifiedParam);

        //todo 校验请求参数

        SortedMap<Object, Object> param = new TreeMap<>();

        Object appId = redisService.getCache(wxConfig.getAppId());
        Object mchId = redisService.getCache(wxConfig.getMchId());
        Object notifyUrl = redisService.getCache(wxConfig.getNotifyUrl());

        //组装请求参数-必须参数
        param.put("appid", appId.toString());
        param.put("mch_id", mchId);
        param.put("nonce_str", WxUtil.CreateNonceString());
        param.put("body", unifiedParam.getBody());
        param.put("out_trade_no", unifiedParam.getOutTradeNo());
        param.put("total_fee", unifiedParam.getTotalFee() + "");
        param.put("spbill_create_ip", unifiedParam.getSpbillCreateIp());
        param.put("notify_url", notifyUrl.toString());
        param.put("trade_type", WxTradeTypeEnum.JSAPI.getType());
//        param.put("openid", unifiedParam.getOpenId());


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
//        if (StringUtils.isNotBlank(unifiedParam.getOpenId())) {
//            param.put("openid", unifiedParam.getOpenId());
//        }

        Object unifiedKey = redisService.getCache(wxConfig.getUnifiedKey());
        Object url = redisService.getCache(wxConfig.getUnifiedOrderUrl());

        //获取签名
        String sign = WxUtil.getSign(param, unifiedKey.toString());
        param.put("sign", sign);
        System.out.println(sign);

        //参数转XML
        String requestXml = WxUtil.getRequestXml(param);

        //请求API
        String result = AuthX509TrustManager.httpsRequest(url.toString(), "POST", requestXml);

        //解析响应体
        logger.info("wxService-wePayUnifiedOrder, apply unified order success, result is {}", result);
        return JaxbUtil.converyToJavaBean(result, UnifiedSuccessDto.class);
    }

}