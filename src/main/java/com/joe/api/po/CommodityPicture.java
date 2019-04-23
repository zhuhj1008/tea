package com.joe.api.po;

import lombok.Data;

import java.util.Date;

/**
 * 商品图片bean
 */
@Data
public class CommodityPicture {

    //图片编号
    private Integer pictureId;

    //商品编号
    private Integer commodityId;

    //图片地址
    private String pictureUrl;

    //图片描述
    private String pictureDesc;

    //创建人
    private Integer createBy;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //可用
    private Boolean enable;
}