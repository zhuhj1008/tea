package com.joe.dto.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CommodityPageParam {

    @ApiModelProperty(value = "类目编号", example = "1")
    private Integer itemId;

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNo;

    @ApiModelProperty(value = "页大小", example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "商品编号", example = "6", notes = "查询商品列表时可以不传")
    private Integer commodityId;
}
