package com.joe.api.po;

import java.util.Date;

public class Evaluate {
    private Integer evaluateId;

    private Integer commodityId;

    private Integer customerId;

    private String customerName;

    private Integer level;

    private String evaluate;

    private String evaluatePicture;

    private Date evaluateTime;

    private String appendEvaluate;

    private Date appendEvaluateTime;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Boolean enable;

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }

    public String getEvaluatePicture() {
        return evaluatePicture;
    }

    public void setEvaluatePicture(String evaluatePicture) {
        this.evaluatePicture = evaluatePicture == null ? null : evaluatePicture.trim();
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getAppendEvaluate() {
        return appendEvaluate;
    }

    public void setAppendEvaluate(String appendEvaluate) {
        this.appendEvaluate = appendEvaluate == null ? null : appendEvaluate.trim();
    }

    public Date getAppendEvaluateTime() {
        return appendEvaluateTime;
    }

    public void setAppendEvaluateTime(Date appendEvaluateTime) {
        this.appendEvaluateTime = appendEvaluateTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}