package com.joe.bussiness.order.service;

import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.bussiness.order.vo.OrderDetailVo;
import com.joe.bussiness.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情业务
 * create by Joe on 2018-06-04 16:05
 **/
@Service
public class OrderDetailWebService {

    @Autowired
    private OrderDetailService orderDetailService;


    public void addOrderDetail(OrderVo orderVo , int orderId) {

        List<OrderDetailVo> orderDetailVoList = OrderVo.convertToOrderDetailVo(orderVo);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailVo orderDetailVo : orderDetailVoList) {
            OrderDetail orderDetail = OrderDetailVo.convert2OrderDetail(orderDetailVo, orderId);
            orderDetailList.add(orderDetail);
        }

        orderDetailService.addOrderDetailBatch(orderDetailList);
    }
}
