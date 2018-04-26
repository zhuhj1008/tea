package com.joe.api.dao;

import com.joe.api.po.UserCustomer;

public interface UserCustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(UserCustomer record);

    int insertSelective(UserCustomer record);

    UserCustomer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(UserCustomer record);

    int updateByPrimaryKey(UserCustomer record);
}