package com.joe.manager.api.order;

import com.alibaba.fastjson.JSON;
import com.joe.manager.BaseTest;
import com.joe.manager.api.order.enums.OrderStatusEnum;
import com.joe.manager.api.order.pojo.OrderDetail;
import com.joe.manager.api.order.service.OrderDetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestOrderDetail extends BaseTest {


    @Autowired
    OrderDetailService orderDetailService;

    @Test
    public void addOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1);
        orderDetail.setDishes("鱼香肉丝");
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        orderDetail.setFlavor("ha辣");
        orderDetail.setOwner("guanyi");
        orderDetail.setAmount(1);
        orderDetail.setStatus(OrderStatusEnum.PAYMENT.getValue());
        int i = orderDetailService.addOrderDetail(orderDetail);
        System.out.println(i);

    }

    @Test
    public void testFindOrderDetail(){
        List<String> statusList = new ArrayList<>();
        statusList.add(OrderStatusEnum.SUBMIT.getValue());
        statusList.add(OrderStatusEnum.PAYMENT.getValue());
        List<OrderDetail> orderDetail = orderDetailService.findOrderDetailByOrderId(1, statusList);
        System.out.println(orderDetail.size());
        System.out.println(JSON.toJSONString(orderDetail));
    }

    @Test
    public void testBatchInsert(){
        List<OrderDetail> orderList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(1);
            orderDetail.setDishes("鱼香肉丝");
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            orderDetail.setFlavor("ha辣");
            orderDetail.setOwner("guanyi");
            orderDetail.setAmount(1);
            orderDetail.setStatus(OrderStatusEnum.PAYMENT.getValue());
            orderList.add(orderDetail);
        }

        int i = orderDetailService.batchInsertOrderDetail(orderList);
        System.out.println(i);
    }

    @Test
    public void testCancelOrderDetail(){
        orderDetailService.cancelDetailByDetailId(10);
    }
}
