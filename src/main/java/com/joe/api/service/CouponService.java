package com.joe.api.service;

import com.joe.api.dao.CouponCustomerMapper;
import com.joe.api.dao.CouponMapper;
import com.joe.api.po.Coupon;
import com.joe.api.po.CouponCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponCustomerMapper couponCustomerMapper;

    /**
     * 添加优惠券
     *
     * @param coupon 优惠券
     * @return 自增主键
     */
    public int addCoupon(Coupon coupon) {

        coupon.setCreateTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setEnable(true);
        couponMapper.insertSelective(coupon);

        return coupon.getCouponId();
    }


    /**
     * 修改优惠券
     *
     * @param coupon 自增主键
     * @return 修改条数
     */
    public int modifyCoupon(Coupon coupon) {
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }


    /**
     * 删除优惠券By优惠券ID
     *
     * @param couponId 优惠券ID
     * @return 删除记录数
     */
    public int dropCoupon(int couponId) {
        Coupon coupon = new Coupon();
        coupon.setCouponId(couponId);
        coupon.setEnable(false);
        coupon.setUpdateTime(new Date());
        int i = couponMapper.updateByPrimaryKeySelective(coupon);

        CouponCustomer couponCustomer = new CouponCustomer();
        couponCustomer.setCouponId(couponId);
        couponCustomer.setEnable(false);
        int i1 = couponCustomerMapper.updateByCouponIdSelective(couponCustomer);

        return i + i1;
    }

    /**
     * 查询优惠券By 优惠券 Id
     *
     * @return 优惠券
     */
    public Coupon queryCouponById(int couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }
}
