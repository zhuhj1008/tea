package com.joe.bussiness.commodity.vo;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;

import java.math.BigDecimal;

/**
 * 商品Vo
 * create by Joe on 2018-05-24 16:45
 **/
public class CommodityVo {

    //名称
    private String pName;

    //类目Id
    private Integer pItem;

    //成本
    private BigDecimal pCOst;

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

    public Commodity commodityVoToCommodity(CommodityVo commodityVo){

        Commodity commodity = new Commodity();
        commodity.setCommodityName(commodityVo.getpName());
        commodity.setPrice(commodityVo.getpPrice());
        commodity.setItemId(commodityVo.getpItem());
        commodity.setPicture(commodityVo.getpImgSmall());

        return commodity;
    }

    public CommodityDetail commodityVoToCommodityDetail(CommodityVo commodityVo){

        CommodityDetail detail =new CommodityDetail();
        detail.setUnit("100g");
        detail.setCost(commodityVo.getpCOst());
        detail.setInitPrice(commodityVo.getpOriginalPrice());
        detail.setStock(commodityVo.getpInventory());
        detail.setProperty(commodityVo.getProperty());
        detail.setDetailPicture(JSON.toJSONString(commodityVo.getpImgBanner()));
        detail.setPictureInfo(JSON.toJSONString(commodityVo.getpImgPresentation()));
        detail.setOrigin(commodityVo.getpPlace());

        return detail;

    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getpItem() {
        return pItem;
    }

    public void setpItem(Integer pItem) {
        this.pItem = pItem;
    }

    public BigDecimal getpCOst() {
        return pCOst;
    }

    public void setpCOst(BigDecimal pCOst) {
        this.pCOst = pCOst;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public BigDecimal getpOriginalPrice() {
        return pOriginalPrice;
    }

    public void setpOriginalPrice(BigDecimal pOriginalPrice) {
        this.pOriginalPrice = pOriginalPrice;
    }

    public Integer getpInventory() {
        return pInventory;
    }

    public void setpInventory(Integer pInventory) {
        this.pInventory = pInventory;
    }

    public String getpPlace() {
        return pPlace;
    }

    public void setpPlace(String pPlace) {
        this.pPlace = pPlace;
    }

    public String getpDescribe() {
        return pDescribe;
    }

    public void setpDescribe(String pDescribe) {
        this.pDescribe = pDescribe;
    }

    public String getpImgSmall() {
        return pImgSmall;
    }

    public void setpImgSmall(String pImgSmall) {
        this.pImgSmall = pImgSmall;
    }

    public String[] getpImgBanner() {
        return pImgBanner;
    }

    public void setpImgBanner(String[] pImgBanner) {
        this.pImgBanner = pImgBanner;
    }

    public String[] getpImgPresentation() {
        return pImgPresentation;
    }

    public void setpImgPresentation(String[] pImgPresentation) {
        this.pImgPresentation = pImgPresentation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


}
