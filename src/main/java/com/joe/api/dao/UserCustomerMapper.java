package com.joe.api.dao;

import com.joe.api.po.UserCustomer;

public interface UserCustomerMapper {

    int insertSelective(UserCustomer record);

    int deleteByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(UserCustomer record);

    UserCustomer selectByPrimaryKey(Integer customerId);

}