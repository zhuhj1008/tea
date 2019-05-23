package com.joe.service;

import com.alibaba.fastjson.JSON;
import com.joe.common.HttpClientUtil;
import com.joe.common.HttpsClientUtil;
import com.joe.common.WxUtil;
import com.joe.common.XmlUtil;
import com.joe.common.exception.BusinessException;
import com.joe.config.ConfigKeyConstant;
import com.joe.dto.wx.UnifiedParam;
import com.joe.dto.wx.UnifiedSuccessDto;
import com.joe.dto.wx.WxLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

//https://developers.weixin.qq.com/miniprogram/dev/index.html
@Slf4j
@Service
public class WxService {


    @Autowired
    private RedisService redisService;

    /**
     * 小程序授权
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/auth.code2Session.html
     */
    public WxLoginDto wxLoginAuthorization(String code) {

        log.info("小程序授权，获取用户openId，请求code：{}。", code);

        String url = redisService.getCache(ConfigKeyConstant.WX_GRANT_URL);
        String appId = redisService.getCache(ConfigKeyConstant.WX_APP_ID);
        String secretKey = redisService.getCache(ConfigKeyConstant.WX_SECRET_KEY);
        if (StringUtils.isAnyBlank(url, appId, secretKey)) {
            log.error("微信授权配置缺失。");
            throw new BusinessException("获取用户信息失败。");
        }

        String requestUrl = url + "?appid=" + appId + "&secret=" + secretKey + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpClientUtil.sendGet(requestUrl);
        log.info("小程序授权，wx响应报文：{}", result);

        WxLoginDto wxLoginDto = JSON.parseObject(result, WxLoginDto.class);
        return wxLoginDto;
    }

    /**
     * 微信统一支付
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     */
    public UnifiedSuccessDto wePayUnifiedOrder(UnifiedParam unifiedParam) {
        log.info("微信统一支付，请求参数：{}", unifiedParam);

        String appId = redisService.getCache(ConfigKeyConstant.WX_APP_ID);
        String mchId = redisService.getCache(ConfigKeyConstant.WEPAY_BUSINESS_CODE);
        String notifyUrl = redisService.getCache(ConfigKeyConstant.WX_NOTIFY_URL);


        //订单金额转换为分,四舍五入不要小数
        BigDecimal totalFee = unifiedParam.getTotalFee();
        if (totalFee == null) {
            throw new BusinessException("订单金额不能为空");
        }
        BigDecimal totalFeeCent = totalFee.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);


        SortedMap<Object, Object> param = new TreeMap<>();
        param.put("appid", appId);
        param.put("mch_id", mchId);
        param.put("nonce_str", WxUtil.createNonceString());
        param.put("body", unifiedParam.getBody());
        param.put("out_trade_no", unifiedParam.getOrderNo());
        param.put("total_fee", totalFeeCent.toString());
        param.put("spbill_create_ip", unifiedParam.getIp());
        param.put("notify_url", notifyUrl);
        param.put("trade_type", "JSAPI");
        param.put("openid", unifiedParam.getOpenId());

        String unifiedKey = redisService.getCache(ConfigKeyConstant.WEPAY_UNIFIED_KEY);
        String url = redisService.getCache(ConfigKeyConstant.WX_UNIFIED_ORDER);

        //获取签名
        String sign = WxUtil.getSign(param, unifiedKey);
        param.put("sign", sign);

        //参数转XML
        String requestXml = WxUtil.getRequestXml(param);
        log.info("微信统一支付，请求报文：{}", requestXml);

        //请求API
        String result = HttpsClientUtil.httpsRequest(url, "POST", requestXml);
        log.info("微信统一支付，响应报文：{}", result);

        //解析响应体
        UnifiedSuccessDto unifiedSuccessDto = XmlUtil.convertToJavaBean(result, UnifiedSuccessDto.class);
        log.info("微信统一支付，请求结果：{}", JSON.toJSON(unifiedSuccessDto));
        return unifiedSuccessDto;
    }

}