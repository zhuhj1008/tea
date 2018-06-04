package com.joe.bussiness.order.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 页面订单信息
 * create by Joe on 2018-06-04 19:14
 **/
public class OrderVo {

    //客户编号
    private Integer customerId;
    //顾客姓名
    private String customerName;
    //订单金额
    private String orderMoney;
    //订单状态
    private Integer orderStatus;
    //快递商家
    private Integer expressId;
    //快递单号
    private String expressCode;
    //快递费
    private BigDecimal expressMoney;
    //收货地址
    private String receiveAddress;
    //买家备注
    private String remake;
    //订单详情
    private String [] orderDetailArr;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String[] getOrderDetailArr() {
        return orderDetailArr;
    }

    public void setOrderDetailArr(String[] orderDetailArr) {
        this.orderDetailArr = orderDetailArr;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
