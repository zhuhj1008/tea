package com.joe.manager.api.coupon;

import com.joe.api.po.CouponCustomer;
import com.joe.api.service.CouponCustomerService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;

import java.util.List;

public class TestCouponCustomerService extends BaseTest {

    @Autowired
    CouponCustomerService couponCustomerService;

    @Test
    public void addCouponCustomer(){
        CouponCustomer couponCustomer = new CouponCustomer();
        couponCustomer.setCustomerId(1);
        couponCustomer.setCouponId(2);
        couponCustomer.setCreateBy(1);
        couponCustomer.setUpdateBy(1);
        int i = couponCustomerService.addCouponCustomer(couponCustomer);
        logger.info("自增主键：{}",i);
    }

    @Test
    public void testSelect(){
        int customerId = 1;
        List<Integer> integers = couponCustomerService.queryCouponIdByCustomerId(customerId);
        logger.info("id为{}的顾客，拥有的优惠券是：{}",customerId,integers);

    }

    @Test
    public void testDrop(){
        int customerId = 1;
        int couponId = 2;

        int i = couponCustomerService.dropCustomerCouponByCouponId(couponId);
        logger.info("根据优惠券Id{}删除记录数：{}",couponId,i);

        int i1 = couponCustomerService.dropCustomerCouponByCustomerId(customerId);
        logger.info("根据顾客Id{}删除记录数：{}",customerId,i1);

    }
}
