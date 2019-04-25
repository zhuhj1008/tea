package com.joe.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class OrderParam {

    //订单编号
    @ApiModelProperty(value = "订单编号", example = "6", notes = "新增时传空")
    private Integer orderId;

    //客户编号
    @ApiModelProperty(value = "客户编号", example = "6", required = true)
    private Integer customerId;

    //客户姓名
    @ApiModelProperty(value = "客户姓名", example = "Joe", required = true)
    private String customerName;

    //订单金额
    @ApiModelProperty(value = "订单金额", example = "666.6")
    private BigDecimal orderMoney;

    //订单状态
    @ApiModelProperty(value = "订单状态", example = "1", required = true)
    private Integer orderStatus;

    //快递编号
    @ApiModelProperty(value = "快递编号", example = "1")
    private Integer expressId;

    //快递单号
    @ApiModelProperty(value = "快递单号", example = "53010000723")
    private String expressCode;

    //快递费
    @ApiModelProperty(value = "快递费", example = "6.66")
    private BigDecimal expressMoney;

    //邮编
    @ApiModelProperty(value = "邮编", example = "10010")
    private String postCode;

    //收货地址
    @ApiModelProperty(value = "收货地址", example = "北京市东城区故宫")
    private String receiveAddress;

    //收件人电话
    @ApiModelProperty(value = "收件人电话", example = "15175225612")
    private String receivePhone;

    //买家备注
    @ApiModelProperty(value = "买家备注", example = "周末送货")
    private String remake;

    //订单详情
    private List<OrderDetailParam> orderDetailArr;
}
