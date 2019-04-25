package com.joe.api.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情bean
 */
@Data
public class OrderDetail {

    //详情编号
    private Integer detailId;

    //订单编号
    private Integer orderId;

    //商品编号
    private Integer commodityId;

    //商品名称
    private String commodityName;

    //商品图片
    private String picture;

    //价格
    private BigDecimal price;

    //数量
    private Integer amount;

    //规格
    private Integer unit;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}