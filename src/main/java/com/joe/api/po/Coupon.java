package com.joe.api.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券Bean
 */
@Data
public class Coupon {

    //优惠券Id
    private Integer couponId;

    //优惠券名字
    private String name;

    //折扣比例
    private BigDecimal discount;

    //折扣金额
    private BigDecimal money;

    //打折数量
    private Integer amount;

    //创建者
    private Integer createBy;

    //创建时间
    private Date createTime;

    //更新者
    private Integer updateBy;

    //更新时间
    private Date updateTime;

    //可用
    private Boolean enable;

}