package com.joe.dto.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class CommodityParam {

    @ApiModelProperty(value = "商品编号", example = "6", notes = "新增时传空")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名称", example = "茶")
    private String commodityName;

    @ApiModelProperty(value = "商品价格", example = "66.66", notes = "不超过两位小数")
    private BigDecimal price;

    @ApiModelProperty(value = "类目编号", example = "1")
    private Integer itemId;

    @ApiModelProperty(value = "小图地址", example = "www.Joe.picture.com")
    private String picture;

    @ApiModelProperty(value = "首页推荐", example = "false")
    private Boolean recommend;

    @ApiModelProperty(value = "描述", example = "美滋滋")
    private String description;

    //明细
    @ApiModelProperty(value = "单位", example = "500g")
    private String unit;

    @ApiModelProperty(value = "成本", example = "6.66", notes = "不超过两位小数")
    private BigDecimal cost;

    @ApiModelProperty(value = "原价", example = "6.66", notes = "不超过两位小数")
    private BigDecimal initPrice;

    @ApiModelProperty(value = "库存", example = "666")
    private Integer stock;

    @ApiModelProperty(value = "口感", example = "贼好")
    private String taste;

    @ApiModelProperty(value = "保存方法", example = "通风干燥")
    private String preservation;

    @ApiModelProperty(value = "保质期", example = "6年")
    private String expirationDate;

    @ApiModelProperty(value = "运费", example = "6.6", notes = "不超过两位小数")
    private BigDecimal freight;

    @ApiModelProperty(value = "产地", example = "北京")
    private String origin;

    @ApiModelProperty(value = "详情图")
    private List<CommodityPictureVo> detailPictures;




}
