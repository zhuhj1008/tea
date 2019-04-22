package com.joe.dto.order;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * 订单发货参数
 * create by Joe on 2018-07-10 11:36
 **/
@Data
public class OrderDeliverDTO {

    //订单编号
    private Integer orderId;

    //快递商家编号
    private Integer expressId;

    //快递单号
    private String expressCode;

    //快递费
    private BigDecimal expressMoney;

}
