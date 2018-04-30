package com.joe.api.dao;

import com.joe.api.po.Coupon;

public interface CouponMapper {

    int deleteByPrimaryKey(Integer couponId);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(Coupon record);

}