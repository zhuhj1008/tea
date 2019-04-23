package com.joe.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求公参")
public class ApiParameter<T> {

    @ApiModelProperty("时间戳")
    private String timeStr;

    @ApiModelProperty("请求验签")
    private String signature;

    @ApiModelProperty("请求私参")
    private T body;
}
