package com.joe.bussiness.order.service;

import com.joe.api.dto.OrderQueryDTO;
import com.joe.api.po.Order;
import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.api.service.OrderService;
import com.joe.bussiness.order.vo.OrderDetailVo;
import com.joe.bussiness.order.vo.OrderVo;
import org.apache.commons.collections.CollectionUtils;
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



    public int addOrder(OrderVo orderVo) {

        Order order = OrderVo.OrderVoConvert2Order(orderVo);
        int orderId = orderService.addOrder(order);

        return orderId;
    }


    public List<OrderVo> getOrderList(OrderQueryDTO dto, int pageNo, int pageSize) {

        List<Order> orders = orderService.queryOrderListByQueryDto(dto, pageNo, pageSize);
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order order : orders) {
            OrderVo orderVo = OrderVo.OrderConvert2OrderVo(order);
            orderVoList.add(orderVo);
        }

        return orderVoList;
    }


    public int getOrderCount(OrderQueryDTO dto) {

        return orderService.queryOrderCountByQueryDto(dto);
    }


}
