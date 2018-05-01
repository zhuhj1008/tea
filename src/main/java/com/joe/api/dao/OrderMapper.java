package com.joe.api.dao;

import com.joe.api.po.Order;

public interface OrderMapper {

    int insertSelective(Order record);

    int deleteByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

}