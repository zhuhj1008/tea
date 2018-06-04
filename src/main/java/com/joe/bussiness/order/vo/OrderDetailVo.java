package com.joe.bussiness.order.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * 页面订单详情信息
 * create by Joe on 2018-06-04 19:14
 **/
public class OrderDetailVo {

    //订单编号
    private Integer orderId;
    //商品编号
    private Integer commodityId;
    //图片
    private String picture;
    //单价
    private BigDecimal price;
    //数量
    private Integer amount;
    //规格
    private Integer unit;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
