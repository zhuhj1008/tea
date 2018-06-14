package com.joe.api.dao;

import com.joe.api.po.ReceiveAddress;

import java.util.List;

public interface ReceiveAddressMapper {

    int insertSelective(ReceiveAddress record);

    int deleteByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(ReceiveAddress record);

    int updateByPrimaryKey(ReceiveAddress record);

    ReceiveAddress selectByPrimaryKey(Integer addressId);

    List<ReceiveAddress> selectByCustomerId(Integer customerId);
}