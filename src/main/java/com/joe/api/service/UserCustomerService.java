package com.joe.api.service;

import com.joe.api.dao.UserCustomerMapper;
import com.joe.api.po.UserCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCustomerService {

    @Autowired
    UserCustomerMapper userCustomerMapper;

    /**
     * 新增客户
     *
     * @param userCustomer
     * @return
     */
    public int addCustomer(UserCustomer userCustomer) {

        userCustomer.setCreateTime(new Date());
        userCustomer.setUpdateTime(new Date());
        userCustomerMapper.insertSelective(userCustomer);
        return userCustomer.getCustomerId();
    }

    /**
     * 修改客户信息
     *
     * @param userCustomer
     * @return
     */
    public int modifyCustomer(UserCustomer userCustomer) {
        return userCustomerMapper.updateByPrimaryKeySelective(userCustomer);
    }

    /**
     * 删除客户（逻辑删除）
     *
     * @param customerId
     * @return
     */
    public int dropCustomer(int customerId) {

        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setCustomerId(customerId);
        userCustomer.setEnable(false);
        return userCustomerMapper.updateByPrimaryKeySelective(userCustomer);
    }

    /**
     * 查询客户By 客户编号
     *
     * @param customerId
     * @return
     */
    public UserCustomer queryCustomerById(int customerId) {

        return userCustomerMapper.selectByPrimaryKey(customerId);
    }
}
