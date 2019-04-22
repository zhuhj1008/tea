package com.joe.dto.order;

import lombok.Data;

/**
 * 订单查询参数
 * create by Joe on 2018-07-10 11:46
 **/
@Data
public class OrderQueryDTO {

    private String customerName;

    private Integer orderStatus;

    private String expressCode;

    private Integer pageNo;

    private Integer pageSize;

}
