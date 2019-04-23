package com.joe.dto.commodity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CommodityParam {

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

    //商品明细
    //单位
    private String unit;

    //成本
    private BigDecimal cost;

    //原价
    private BigDecimal initPrice;

    //库存
    private Integer stock;

    //口感
    private String taste;

    //保存方法
    private String preservation;

    //保质期
    private String expirationDate;

    //运费
    private BigDecimal freight;

    //积分
    private Integer integral;

    //产地
    private String origin;

    //商品详情图
    private List<Picture> detailPictures;

    @Data
    public class Picture{
        //图片地址
        private String pictureUrl;
        //图片描述
        private String pictureDesc;
    }

}
