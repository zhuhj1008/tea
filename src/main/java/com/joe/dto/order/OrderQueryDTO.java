package com.joe.dto.order;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 订单查询参数
 * create by Joe on 2018-07-10 11:46
 **/
public class OrderQueryDTO {

    private String customerName;

    private Integer orderStatus;

    private String expressCode;

    private Integer pageNo;

    private Integer pageSize;

    public OrderQueryDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
