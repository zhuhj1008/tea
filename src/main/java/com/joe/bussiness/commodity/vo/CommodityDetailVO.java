package com.joe.bussiness.commodity.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 商品详情VO
 * create by Joe on 2018-05-29 10:57
 **/
public class CommodityDetailVO {

    //商品名称
    private String name;

    //商品类目
    private Integer itemId;

    //小图地址
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

    //库存
    private Integer stock;

    //细节图
    private String detailPicture;

    //图信息
    private String pictureInfo;

    //产地
    private String origin;

    //口味
    private String flavor;

    //保存方法
    private String saveMethod;

    //保质期
    private String saveDate;

    public static CommodityDetailVO commodity2DetailVo(CommodityDetailVO vo,Commodity commodity){

        if(commodity == null){
            return vo;
        }

        vo.setName(commodity.getCommodityName());
        vo.setSmallPicture(commodity.getPicture());
        vo.setItemId(commodity.getItemId());
        vo.setPrice(commodity.getPrice());

        return vo;

    }

    public static CommodityDetailVO commodityDetail2DetailVo(CommodityDetailVO vo,CommodityDetail commodityDetail){

        if(commodityDetail == null){
            return vo;
        }

        vo.setDetailId(commodityDetail.getDetailId());
        vo.setCommodityId(commodityDetail.getCommodityId());
        vo.setCost(commodityDetail.getCost());
        vo.setInitPrice(commodityDetail.getInitPrice());
        vo.setStock(commodityDetail.getStock());
        vo.setDetailPicture(commodityDetail.getDetailPicture());
        vo.setPictureInfo(commodityDetail.getPictureInfo());
        vo.setOrigin(commodityDetail.getOrigin());

        if(StringUtils.isNoneEmpty(commodityDetail.getProperty())){
            JSONObject property =(JSONObject) JSON.parse(commodityDetail.getProperty());
            vo.setFlavor(property.getString("pTaste"));
            vo.setSaveMethod(property.getString("pPreservation"));
            vo.setSaveDate(property.getString("pExpirationDate"));
        }

        return vo;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetailPicture() {
        return detailPicture;
    }

    public void setDetailPicture(String detailPicture) {
        this.detailPicture = detailPicture;
    }

    public String getPictureInfo() {
        return pictureInfo;
    }

    public void setPictureInfo(String pictureInfo) {
        this.pictureInfo = pictureInfo;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSaveMethod() {
        return saveMethod;
    }

    public void setSaveMethod(String saveMethod) {
        this.saveMethod = saveMethod;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }
}
