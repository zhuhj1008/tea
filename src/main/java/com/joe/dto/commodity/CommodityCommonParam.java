package com.joe.dto.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CommodityCommonParam {

    @ApiModelProperty(value = "商品编号", example = "6")
    private Integer commodityId;
}
