package com.joe.api.dao;

import com.joe.api.po.CouponCustomer;

public interface CouponCustomerMapper {
    int deleteByPrimaryKey(Integer customerCouponId);

    int insert(CouponCustomer record);

    int insertSelective(CouponCustomer record);

    CouponCustomer selectByPrimaryKey(Integer customerCouponId);

    int updateByPrimaryKeySelective(CouponCustomer record);

    int updateByPrimaryKey(CouponCustomer record);
}