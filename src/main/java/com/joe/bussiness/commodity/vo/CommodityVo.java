package com.joe.bussiness.commodity.vo;

import java.math.BigDecimal;

/**
 * 商品Vo
 * create by Joe on 2018-05-24 16:45
 **/
public class CommodityVo {

    /**
     * 名称
     */
    private String name;

    /**
     * 类目
     */
    private Integer item;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer Stock;

    /**
     * 图片地址
     */
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
