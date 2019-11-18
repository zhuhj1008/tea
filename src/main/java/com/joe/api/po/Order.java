package com.joe.api.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单Bean
 */
@Data
public class Order {

    //订单编号
    private Integer orderId;

    //订单号
    private String orderNo;

    //顾客编号
    private Integer customerId;

    //顾客姓名
    private String customerName;

    //订单金额
    private BigDecimal orderMoney;

    //订单状态
    private Integer orderStatus;

    //支付时间
    private Date paymentTime;

    //邮编
    private String postCode;

    //快递公司编号
    private Integer expressId;

    //快递单号
    private String expressCode;

    //邮费
    private BigDecimal expressMoney;

    //收货地址
    private String receiveAddress;

    //收件人电话
    private String receivePhone;

    //备注
    private String remake;

    //创建人
    private Integer createBy;

    //创建时间
    private Date createTime;

    //更新人
    private Integer updateBy;

    //发货时间
    private Date deliveryTime;

    //收货时间
    private Date receiveTime;

    //可用
    private Boolean enable;

}