package com.joe.api.dao;

import com.joe.api.po.CouponCustomer;

import java.util.List;

public interface CouponCustomerMapper {

    int insertSelective(CouponCustomer record);

    int deleteByPrimaryKey(Integer customerCouponId);

    int updateByPrimaryKeySelective(CouponCustomer record);

    int updateByCustomerIdSelective(CouponCustomer record);

    int updateByCouponIdSelective(CouponCustomer record);

    CouponCustomer selectByPrimaryKey(Integer customerCouponId);

    List<Integer> selectByCustomerId(Integer customerId);

}