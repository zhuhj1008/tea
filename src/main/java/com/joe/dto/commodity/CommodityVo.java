package com.joe.dto.commodity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品Vo
 * create by Joe on 2018-05-24 16:45
 **/
@Data
public class CommodityVo {

    //商品ID
    private Integer commodityId;

    //商品名称
    private String commodityName;

    //商品价格
    private BigDecimal price;

    //商品类目ID
    private Integer itemId;

    //商品小图地址
    private String picture;

    //品牌
    private Integer brand;

    //首页推荐
    private Boolean recommend;

    //描述
    private String description;

}
