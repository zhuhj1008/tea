package com.joe.api.po;

import java.util.Date;

public class ShopCartDetail {
    private Integer detailId;

    private Integer shopCartId;

    private Integer commodityId;

    private Integer commodityAmont;

    private Date createTime;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Integer shopCartId) {
        this.shopCartId = shopCartId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityAmont() {
        return commodityAmont;
    }

    public void setCommodityAmont(Integer commodityAmont) {
        this.commodityAmont = commodityAmont;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}