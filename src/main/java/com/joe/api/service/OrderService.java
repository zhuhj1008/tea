package com.joe.api.service;

import com.joe.api.dao.OrderMapper;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 创建新订单
     *
     * @param order
     * @return
     */
    public int addOrder(Order order) {

        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setOrderStatus(OrderStatusEnum.NEW.getValue());
        orderMapper.insertSelective(order);
        return order.getOrderId();
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 订单号
     */
    public int modifyOrder(Order order) {

        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除订单（逻辑删除）By 订单号
     *
     * @param orderId 订单号
     * @return 删除记录数
     */
    public int deleteOrder(int orderId) {

        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(OrderStatusEnum.CANCEL.getValue());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 查询订单By 订单号
     *
     * @param orderId 订单号
     * @return 订单
     */
    public Order queryOrder(int orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }


}
