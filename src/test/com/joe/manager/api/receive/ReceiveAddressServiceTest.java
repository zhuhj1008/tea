package com.joe.manager.api.receive;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.ReceiveAddress;
import com.joe.api.service.ReceiveAddressService;
import com.joe.common.base.BaseService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收货地址测试类
 * create by Joe on 2018-06-14 15:54
 **/
public class ReceiveAddressServiceTest extends BaseTest{

    @Autowired
    ReceiveAddressService receiveAddressService;

    @Test
    public void addReceiveAddressTest(){

        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setCustomerId(2);
        receiveAddress.setName("周冠一");
        receiveAddress.setMobilePhone("15175225612");
        receiveAddress.setProvince("河北省");
        receiveAddress.setCity("保定");
        receiveAddress.setArea("保定区");
        receiveAddress.setStreet("第六大街");
        receiveAddress.setAddressDetail("吧啦吧啦");

        logger.info("新增收货地址:{}", JSON.toJSONString(receiveAddress));
        int i = receiveAddressService.addReceiveAddress(receiveAddress);
        logger.info("新增收货地址成功，编号:{}",i);
    }

}
