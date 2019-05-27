package com.joe.dto.order;

import lombok.Data;

/**
 * 客户订单查询参数
 * create by Joe on 2018-07-10 11:46
 **/
@Data
public class CustomerOrderParam {

    private Integer customerId;

    private Integer pageNo;

    private Integer pageSize;

}
