package com.joe.api.po;

import lombok.Data;

import java.util.Date;

/**
 * 优惠券bean
 */
@Data
public class CouponCustomer {

    private Integer customerCouponId;

    private Integer customerId;

    private Integer couponId;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Boolean enable;

}