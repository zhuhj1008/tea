package com.joe.manager.api.order;

import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.bussiness.order.service.OrderDetailWebService;
import com.joe.bussiness.order.vo.OrderDetailVo;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情测试类
 * create by Joe on 2018-06-06 15:50
 **/
public class OrderDetailServiceTest extends BaseTest{

    @Autowired
    OrderDetailService orderDetailService;

    @Test
    public void addOrderDetailBatchTest(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1);
        orderDetail.setCommodityId(61);
        orderDetail.setPrice(new BigDecimal(12));
        orderDetail.setAmount(2);
        orderDetail.setPicture("www.adaasdada.com");
        orderDetail.setUnit(150);
        orderDetails.add(orderDetail);
        orderDetailService.addOrderDetailBatch(orderDetails);
    }



}
