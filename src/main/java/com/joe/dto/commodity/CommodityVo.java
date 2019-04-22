package com.joe.dto.commodity;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品Vo
 * create by Joe on 2018-05-24 16:45
 **/
@Data
public class CommodityVo {

    //商品id
    private Integer pId;

    //名称
    private String pName;

    //类目Id
    private Integer pItem;

    //是否首页推荐
    private Boolean pRecommend;

    //成本
    private BigDecimal pCost;

    //售价
    private BigDecimal pPrice;

    //原价
    private BigDecimal pOriginalPrice;

    //库存
    private Integer pInventory;

    //产地
    private String pPlace;

    //描述
    private String pDescribe;

    //小图地址
    private String pImgSmall;

    //滚动图地址
    private String[] pImgBanner;

    //详情图地址
    private String[] pImgPresentation;

    //口感，保存方法，保质期
    private String property;

    public Commodity commodityVoToCommodity(CommodityVo commodityVo) {

        Commodity commodity = new Commodity();
        if (commodityVo.getPId() != null) {
            commodity.setCommodityId(commodityVo.getPId());
        }
        commodity.setCommodityName(commodityVo.getPName());
        commodity.setPrice(commodityVo.getPPrice());
        commodity.setItemId(commodityVo.getPItem());
        commodity.setRecommend(commodityVo.getPRecommend());
        commodity.setPicture(commodityVo.getPImgSmall());
        commodity.setDescription(commodityVo.getPDescribe());

        return commodity;
    }

    public CommodityDetail commodityVoToCommodityDetail(CommodityVo commodityVo) {

        CommodityDetail detail = new CommodityDetail();
        if (commodityVo.getPId() != null) {
            detail.setCommodityId(commodityVo.getPId());
        }
        detail.setUnit("100g");
        detail.setCost(commodityVo.getPCost());
        detail.setInitPrice(commodityVo.getPOriginalPrice());
        detail.setStock(commodityVo.getPInventory());
        detail.setProperty(commodityVo.getProperty());
        detail.setDetailPicture(JSON.toJSONString(commodityVo.getPImgBanner()));
        detail.setPictureInfo(JSON.toJSONString(commodityVo.getPImgPresentation()));
        detail.setOrigin(commodityVo.getPPlace());

        return detail;

    }


}
