package com.joe.dto.order;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * 订单发货参数
 * create by Joe on 2018-07-10 11:36
 **/
public class OrderDeliverDTO {

    //订单编号
    private Integer orderId;

    //快递商家编号
    private Integer expressId;

    //快递单号
    private String expressCode;

    //快递费
    private BigDecimal expressMoney;

    public OrderDeliverDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public BigDecimal getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(BigDecimal expressMoney) {
        this.expressMoney = expressMoney;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
