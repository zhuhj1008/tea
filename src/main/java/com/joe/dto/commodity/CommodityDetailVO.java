package com.joe.dto.commodity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品详情VO
 * create by Joe on 2018-05-29 10:57
 **/
@Data
public class CommodityDetailVO {

    //商品名称
    private String name;

    //商品类目
    private Integer itemId;

    //商品banner图
    private String smallPicture;

    //售价
    private BigDecimal price;

    //明细编号
    private Integer detailId;

    //商品编号
    private Integer commodityId;

    //成本
    private BigDecimal cost;

    //原价
    private BigDecimal initPrice;

    //描述
    private String description;

    //是否首页展示
    private Boolean recommend;

    //库存
    private Integer stock;

    //产地
    private String origin;

    //口味
    private String flavor;

    //保存方法
    private String saveMethod;

    //保质期
    private String saveDate;

    //商品详情图
    private List<Picture> pictures;

    @Data
    private class Picture{

        private String pictureUrl;

        private String pictureDesc;
    }

}
