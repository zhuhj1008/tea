package com.joe.api.dao;

import com.joe.api.po.ReceiveAddress;

public interface ReceiveAddressMapper {

    int insertSelective(ReceiveAddress record);

    int deleteByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(ReceiveAddress record);

    int updateByPrimaryKey(ReceiveAddress record);

    ReceiveAddress selectByPrimaryKey(Integer addressId);
}