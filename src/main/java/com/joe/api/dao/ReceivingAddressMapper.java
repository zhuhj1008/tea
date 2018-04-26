package com.joe.api.dao;

import com.joe.api.po.ReceivingAddress;

public interface ReceivingAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(ReceivingAddress record);

    int insertSelective(ReceivingAddress record);

    ReceivingAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(ReceivingAddress record);

    int updateByPrimaryKey(ReceivingAddress record);
}