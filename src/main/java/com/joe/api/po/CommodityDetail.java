package com.joe.api.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品明细Bean
 */
@Data
public class CommodityDetail {

    //明细ID
    private Integer detailId;

    //商品Id
    private Integer commodityId;

    //单位
    private String unit;

    //成本
    private BigDecimal cost;

    //原价
    private BigDecimal initPrice;

    //库存
    private Integer stock;

    //属性
    private String property;

    //细节图
    private String detailPicture;

    //图信息
    private String pictureInfo;

    //运费
    private BigDecimal freight;

    //积分
    private Integer integral;

    //产地
    private String origin;

    //可用
    private Boolean enable;

}