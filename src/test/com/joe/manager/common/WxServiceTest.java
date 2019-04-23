package com.joe.manager.common;

import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.common.wx.dto.UnifiedSuccessDto;
import com.joe.common.wx.dto.WxConfig;
import com.joe.common.wx.service.WxService;
import com.joe.manager.BaseTest;
import com.joe.service.RedisService;
import com.joe.util.http.HttpClientUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
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
        Map param = new HashMap();
        String code = "0615RBY725zmsS0Xq1X725epY725RBYb";
        param.put("appid", "wx163389ac6f473216");
        param.put("secret", "b0dab0e3e2833d3e8bb20ebd059ca558");
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
