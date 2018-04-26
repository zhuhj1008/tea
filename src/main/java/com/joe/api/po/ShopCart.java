package com.joe.api.po;

import java.util.Date;

public class ShopCart {
    private Integer shopCartId;

    private Integer customerId;

    private Date caretaTime;

    public Integer getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Integer shopCartId) {
        this.shopCartId = shopCartId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCaretaTime() {
        return caretaTime;
    }

    public void setCaretaTime(Date caretaTime) {
        this.caretaTime = caretaTime;
    }
}