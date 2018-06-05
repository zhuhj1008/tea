package com.joe.bussiness.order.service;

import com.joe.api.po.Order;
import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.api.service.OrderService;
import com.joe.bussiness.order.dto.OrderQueryDTO;
import com.joe.bussiness.order.vo.OrderDetailVo;
import com.joe.bussiness.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单业务
 * create by Joe on 2018-06-04 16:03
 **/
@Service
public class OrderWebService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    public int addOrder(OrderVo orderVo) {

        Order order = OrderVo.convert2Order(orderVo);
        int orderId = orderService.addOrder(order);

        List<OrderDetailVo> orderDetailVoList = OrderVo.convertToOrderDetailVo(orderVo);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailVo orderDetailVo : orderDetailVoList) {
            OrderDetail orderDetail = OrderDetailVo.convert2OrderDetail(orderDetailVo, orderId);
            orderDetailList.add(orderDetail);
        }

        orderDetailService.addOrderDetailBatch(orderDetailList);

        return orderId;
    }

    public List<OrderVo> getOrderList(OrderQueryDTO dto) {




        return null;
    }


}
