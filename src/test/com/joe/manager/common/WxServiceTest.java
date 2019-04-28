package com.joe.manager.common;

import com.joe.service.WxService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 微信服务测试类
 * create by Joe on 2018-08-08 16:53
 **/
public class WxServiceTest extends BaseTest {

    @Autowired
    private WxService wxService;


    @Test
    public void testAuth() {
        String code = "061X7Jj82KSTKJ0zqAj82arAj82X7JjY";
        wxService.wxLoginAuthorization(code);
    }

    @Test
    public void testUnifiedOrder() {

    }
}
