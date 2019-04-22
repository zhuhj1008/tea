package com.joe.api.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品bean
 */
@Data
public class Commodity {

    //商品ID
    private Integer commodityId;

    //商品名称
    private String commodityName;

    //商品价格
    private BigDecimal price;

    //商品类目ID
    private Integer itemId;

    //商品图片地址
    private String picture;

    //品牌
    private Integer brand;

    //首页推荐
    private Boolean recommend;

    //描述
    private String description;

    //预留字段
    private String extends2;

    //创建者
    private Integer createBy;

    //更新者
    private Integer updateBy;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //可用
    private Boolean enable;

}