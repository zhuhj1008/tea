package com.joe.api.dao;

import com.joe.api.dto.OrderQueryDTO;
import com.joe.api.po.Order;

import java.util.List;

public interface OrderMapper {

    int insertSelective(Order record);

    int deleteByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectByOrderQueryDtoSelective(OrderQueryDTO orderQueryDTO);

}