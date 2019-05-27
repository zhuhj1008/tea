package com.joe.api.dao;

import com.joe.api.po.Order;

import java.util.List;

public interface OrderMapper {

    int insertSelective(Order record);

    int deleteByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    Integer selectCountByOrderSelective(Order order);

    List<Order> selectByOrderSelective(Order order);

    List<Order> selectByCustomerId(Integer customerId);

}