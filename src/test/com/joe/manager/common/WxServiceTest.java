package com.joe.manager.common;

import com.joe.common.qiniu.QiNiuConfig;
import com.joe.common.redis.RedisService;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.common.wx.dto.UnifiedSuccessDto;
import com.joe.common.wx.dto.WxConfig;
import com.joe.common.wx.service.WxService;
import com.joe.manager.BaseTest;
import com.joe.util.http.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 微信服务测试类
 * create by Joe on 2018-08-08 16:53
 **/
public class WxServiceTest extends BaseTest {

    @Autowired
    private WxService wxService;

    @Autowired
    private WxConfig wxConfig;
    
    @Autowired
    private RedisService redisService;

    @Test
    public void testAA(){
        Map param = new HashedMap();
        String code = "071rs8aG1dA9X00Gnb8G1RqY9G1rs8aI";
        param.put("appid", "wx7787aa5b85cd4a58");
        param.put("secret", "723f7eb41927cbd33106ac322be458c3");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String s = HttpClientUtil.doPost(url, param, "UTF-8");
        System.out.println(s);

    }

    @Test
    public void testProperties(){
        System.out.println(wxConfig);
        Object cache = redisService.getCache(wxConfig.getAppId());
        System.out.println(cache);
    }

    @Test
    public void testAuth() {
        String code = "021ouLNC0e8eHd24PlNC0HUGNC0ouLNC";
        String openIdAndSessionKey = wxService.getOpenIdAndSessionKey(code);
        System.out.println(openIdAndSessionKey);
    }

    @Test
    public void testUnifiedOrder(){

        UnifiedParamDto param = new UnifiedParamDto();
        param.setBody("商品支付");
        param.setOutTradeNo(22+"");
        param.setTotalFee(8890);
        param.setSpbillCreateIp("1.2.3.5");
        param.setOpenId("");

        UnifiedSuccessDto unifiedSuccessDto = wxService.wePayUnifiedOrder(param);
        System.out.println(unifiedSuccessDto);
    }
}
