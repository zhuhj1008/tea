package com.joe.dto.order;

import com.joe.api.po.OrderDetail;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * 页面订单详情信息
 * create by Joe on 2018-06-04 19:14
 **/
@Data
public class OrderDetailVo {

    //订单编号
    private Integer orderId;
    //商品编号
    private Integer commodityId;
    //商品名称
    private String commodityName;
    //图片
    private String picture;
    //单价
    private BigDecimal price;
    //数量
    private Integer amount;
    //规格
    private Integer unit;


    public static OrderDetail convert2OrderDetail(OrderDetailVo orderDetailVo, int orderId) {

        if (orderDetailVo == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setCommodityId(orderDetailVo.getCommodityId());
        orderDetail.setCommodityName(orderDetailVo.getCommodityName());
        orderDetail.setPicture(orderDetailVo.getPicture());
        orderDetail.setPrice(orderDetailVo.getPrice());
        orderDetail.setAmount(orderDetailVo.getAmount());
        orderDetail.setUnit(orderDetailVo.getUnit());
        return orderDetail;
    }

}
