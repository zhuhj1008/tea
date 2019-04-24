package com.joe.dto.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品评价参数
 */
@Data
@ApiModel
public class EvaluateParam {

    @ApiModelProperty(value = "评价编号", example = "6",notes = "新增时传空")
    private Integer evaluateId;

    @ApiModelProperty(value = "商品编号", example = "6")
    private Integer commodityId;

    @ApiModelProperty(value = "客户编号", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "客户姓名", example = "Joe")
    private String customerName;

    @ApiModelProperty(value = "评价", example = "非常的nice")
    private String evaluate;

    @ApiModelProperty(value = "评价图", example = "www.joe.picture.com")
    private String evaluatePicture;

    @ApiModelProperty(value = "追加评价", example = "真的非常的nice")
    private String appendEvaluate;
}
