package com.joe.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel
public class OrderDetailParam {

    @ApiModelProperty(value = "商品编号", example = "6")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名称", example = "茶")
    private String commodityName;

    @ApiModelProperty(value = "图片", example = "www.joe.picture.com")
    private String picture;

    @ApiModelProperty(value = "单价", example = "6.66")
    private BigDecimal price;

    @ApiModelProperty(value = "数量", example = "6")
    private Integer amount;

    @ApiModelProperty(value = "规格", example = "66")
    private Integer unit;
}
