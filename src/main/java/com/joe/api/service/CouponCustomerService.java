package com.joe.api.service;

import com.joe.api.dao.CouponCustomerMapper;
import com.joe.api.po.CouponCustomer;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponCustomerService {

    @Autowired
    CouponCustomerMapper couponCustomerMapper;

    /**
     * 新增 顾客-优惠券
     *
     * @param couponCustomer
     * @return
     */
    public int addCouponCustomer(CouponCustomer couponCustomer) {

        couponCustomer.setCreateTime(new Date());
        couponCustomer.setUpdateTime(new Date());
        couponCustomer.setEnable(true);
        couponCustomerMapper.insertSelective(couponCustomer);
        return couponCustomer.getCustomerCouponId();
    }


    /**
     * 删除By ID
     * @param couponCustomerId
     * @return
     */
    public int dropCustomerCouponById(int couponCustomerId) {

        CouponCustomer couponCustomer = new CouponCustomer();
        couponCustomer.setCustomerCouponId(couponCustomerId);
        couponCustomer.setEnable(false);
        return couponCustomerMapper.updateByPrimaryKeySelective(couponCustomer);
    }

    /**
     * 删除By 顾客ID
     * @param customerId
     * @return
     */
    public int dropCustomerCouponByCustomerId(int customerId){

        CouponCustomer couponCustomer = new CouponCustomer();
        couponCustomer.setCustomerId(customerId);
        couponCustomer.setEnable(false);
        return couponCustomerMapper.updateByCustomerIdSelective(couponCustomer);

    }

    /**
     * 删除By 优惠券ID
     * @param couponId
     * @return
     */
    public int dropCustomerCouponByCouponId(int couponId){

        CouponCustomer couponCustomer = new CouponCustomer();
        couponCustomer.setCouponId(couponId);
        couponCustomer.setEnable(false);
        return couponCustomerMapper.updateByCouponIdSelective(couponCustomer);
    }


    /**
     * 查询 By主键
     * @param couponCustomerId
     * @return
     */
    public CouponCustomer queryCouponCustomerById(int couponCustomerId){

        return  couponCustomerMapper.selectByPrimaryKey(couponCustomerId);
    }


    /**
     * 查询By 顾客Id
     * @param customerId  顾客ID
     * @return
     */
    public List<Integer> queryCouponIdByCustomerId(int customerId){

        return couponCustomerMapper.selectByCustomerId(customerId);
    }


}
