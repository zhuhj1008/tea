package com.joe.api.service;

import com.github.pagehelper.PageHelper;
import com.joe.api.dao.OrderMapper;
import com.joe.api.dto.OrderQueryDTO;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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


    /**
     * 根据条件查询订单个数
     *
     * @param orderQueryDTO
     * @return
     */
    public int queryOrderCountByQueryDto(OrderQueryDTO orderQueryDTO) {

        return orderMapper.selectCountByOrderQueryDtoSelective(orderQueryDTO);
    }

    /**
     * 根据条件查询订单
     *
     * @param orderQueryDTO
     * @return
     */
    public List<Order> queryOrderListByQueryDto(OrderQueryDTO orderQueryDTO, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);

        return orderMapper.selectByOrderQueryDtoSelective(orderQueryDTO);
    }


}
