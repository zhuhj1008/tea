package com.joe.api.dao;

import com.joe.api.po.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {

    int insertSelective(OrderDetail record);

    int insertBatch(@Param(value = "orderDetailList") List<OrderDetail> orderDetailList);

    int deleteByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateStatusByOrderId(@Param(value = "orderId") Integer orderId, @Param(value = "status") Integer status);

    OrderDetail selectByPrimaryKey(Integer detailId);

    List<OrderDetail> selectByCustomerIdAndStatus(@Param(value = "customerId") int customerId, @Param(value = "status") int status);

    List<OrderDetail> queryOrderDetailByOrderId(Integer orderId);
}