package com.joe.api.po;

import lombok.Data;

import java.util.Date;

/**
 * 商品评价bean
 */
@Data
public class CommodityEvaluate {

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

}