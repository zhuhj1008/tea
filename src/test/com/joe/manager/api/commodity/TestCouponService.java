package com.joe.manager.api.commodity;

import com.joe.api.po.Coupon;
import com.joe.api.service.CouponCustomerService;
import com.joe.api.service.CouponService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TestCouponService extends BaseTest {

    @Autowired
    CouponService couponService;

    @Autowired
    CouponCustomerService couponCustomerService;

    @Test
    public void testAdd(){

//        Coupon coupon = new Coupon();
//        coupon.setName("五一八折优惠");
//        coupon.setDiscount(new BigDecimal(0.8));
//        coupon.setMoney(new BigDecimal(0.00));
//        coupon.setAmount(1);
//        coupon.setCreateBy(1);
//        coupon.setUpdateBy(1);
//        couponService.addCoupon(coupon);

        Coupon coupon = new Coupon();
        coupon.setName("五一满两件减10");
        coupon.setDiscount(new BigDecimal(0.00));
        coupon.setMoney(new BigDecimal(10.00));
        coupon.setAmount(2);
        coupon.setCreateBy(1);
        coupon.setUpdateBy(1);
        int i = couponService.addCoupon(coupon);
        logger.info("自增主键:{}",i);
    }


    @Test
    public void testDrop(){
        int couponId= 1;
        int i = couponService.dropCoupon(couponId);
        logger.info("删除记录数：{}",i);
    }

    @Test
    public void testSelect(){
        Coupon coupon = couponService.queryCouponById(2);
        logger.info("id为2的优惠券是：{}",coupon);
    }


}
