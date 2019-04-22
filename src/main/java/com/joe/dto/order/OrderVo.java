package com.joe.dto.order;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.Order;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 页面订单信息
 * create by Joe on 2018-06-04 19:14
 **/
public class OrderVo {

    //订单编号
    private Integer orderId;
    //客户编号
    private Integer customerId;
    //顾客姓名
    private String customerName;
    //订单金额
    private BigDecimal orderMoney;
    //订单状态
    private Integer orderStatus;
    //快递商家
    private Integer expressId;
    //快递单号
    private String expressCode;
    //快递费
    private BigDecimal expressMoney;
    //邮编
    private String postCode;
    //收货地址
    private String receiveAddress;
    //收件人电话
    private String receivePhone;
    //买家备注
    private String remake;
    //订单创建时间
    private String crateTime;
    //发货时间
    private String deliveryTime;
    //收货时间
    private String receiveTime;
    //订单详情
    private String orderDetailArr;

    public static void main(String[] args) throws ParseException {

        Date date = new Date();
        String sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(sDate);


//        List<OrderDetailVo> orderDetailVos = new ArrayList<>();
//        OrderDetailVo orderDetailVo = new OrderDetailVo();
//        orderDetailVo.setCommodityId(61);
//        orderDetailVo.setPrice(new BigDecimal(12));
//        orderDetailVo.setAmount(2);
//        orderDetailVo.setPicture("www.adaasdada.com");
//        orderDetailVo.setUnit(150);
//        orderDetailVos.add(orderDetailVo);
//        String orderDetails = JSON.toJSONString(orderDetailVos);
//        System.out.println(JSON.toJSONString(orderDetailVos));
//
//        OrderVo orderVo = new OrderVo();
//        orderVo.setCustomerId(1);
//        orderVo.setCustomerName("joe");
//        orderVo.setOrderMoney(new BigDecimal(10));
//        orderVo.setOrderStatus(1);
//        orderVo.setExpressId(2);
//        orderVo.setExpressCode("162358974126645");
//        orderVo.setExpressMoney(new BigDecimal(0.6));
//        orderVo.setReceiveAddress("北京市海淀区上地三街");
//        orderVo.setRemake("尽快发货");
//        orderVo.setOrderDetailArr(orderDetails);
//
//        System.out.println(JSON.toJSONString(orderVo));

    }


    public static Order OrderVoConvert2Order(OrderVo orderVo) {

        if (orderVo == null) {
            return null;
        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Order order = new Order();
        order.setOrderId(orderVo.getOrderId());
        order.setCustomerId(orderVo.getCustomerId());
        order.setCustomerName(orderVo.getCustomerName());
        order.setOrderMoney(orderVo.getOrderMoney());
        order.setOrderStatus(orderVo.getOrderStatus());
        order.setExpressId(orderVo.getExpressId());
        order.setExpressCode(orderVo.getExpressCode());
        order.setExpressMoney(orderVo.getExpressMoney());
        order.setPostCode(orderVo.getPostCode());
        order.setReceiveAddress(orderVo.getReceiveAddress());
        order.setReceivePhone(orderVo.getReceivePhone());
        order.setRemake(orderVo.getRemake());
//        order.setCreateTime(sdf.parse(orderVo.getCrateTime()));
//        order.setDeliveryTime(sdf.parse(orderVo.getDeliveryTime()));
//        order.setReceiveTime(sdf.parse(orderVo.getReceiveTime()));
        return order;
    }

    public static OrderVo OrderConvert2OrderVo(Order order) {
        if (order == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(order.getOrderId());
        orderVo.setCustomerId(order.getCustomerId());
        orderVo.setCustomerName(order.getCustomerName());
        orderVo.setOrderMoney(order.getOrderMoney());
        orderVo.setOrderStatus(order.getOrderStatus());
        orderVo.setExpressId(order.getExpressId());
        orderVo.setExpressCode(order.getExpressCode());
        orderVo.setExpressMoney(order.getExpressMoney());
        orderVo.setPostCode(order.getPostCode());
        orderVo.setReceiveAddress(order.getReceiveAddress());
        orderVo.setReceivePhone(order.getReceivePhone());
        orderVo.setRemake(order.getRemake());

        if (order.getCreateTime() != null) {
            orderVo.setCrateTime(sdf.format(order.getCreateTime()));
        }
        if (order.getDeliveryTime() != null) {
            orderVo.setDeliveryTime(sdf.format(order.getDeliveryTime()));
        }
        if (order.getReceiveTime() != null) {
            orderVo.setReceiveTime(sdf.format(order.getReceiveTime()));

        }
        return orderVo;
    }


    public static List<OrderDetailVo> convertToOrderDetailVo(OrderVo orderVo) {

        if (orderVo == null || StringUtils.isBlank(orderVo.getOrderDetailArr())) {
            return null;
        }

        String orderDetailArr = orderVo.getOrderDetailArr();
        return JSON.parseArray(orderDetailArr, OrderDetailVo.class);
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getOrderDetailArr() {
        return orderDetailArr;
    }

    public void setOrderDetailArr(String orderDetailArr) {
        this.orderDetailArr = orderDetailArr;
    }

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String crateTime) {
        this.crateTime = crateTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
