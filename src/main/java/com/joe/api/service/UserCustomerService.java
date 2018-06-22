package com.joe.api.service;

import com.joe.api.dao.UserCustomerMapper;
import com.joe.api.po.UserCustomer;
import org.apache.commons.lang3.StringUtils;
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
    public UserCustomer queryCustomerByCustomerId(int customerId) {

        return userCustomerMapper.selectByPrimaryKey(customerId);
    }

    /**
     * 查询客户 by 微信唯一标识
     * @param openId
     * @return
     */
    public UserCustomer queryCustomerByOpenId(String openId){

        if(StringUtils.isEmpty(openId)){
            return null;
        }
        return userCustomerMapper.queryCustomerByOpenId(openId);
    }

    /**
     * 查询客户 by 小程序唯一标识
     * @param unionId
     * @return
     */
    public UserCustomer queryCustomerByUnionId(String unionId){

        if(StringUtils.isEmpty(unionId)){
            return null;
        }
        return userCustomerMapper.queryCustomerByUnionId(unionId);
    }


}
