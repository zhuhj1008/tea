package com.joe.dto.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public  class CommodityPictureVo {

    @ApiModelProperty(value = "图片地址", example = "www.Joe.picture.com")
    private String pictureUrl;

    @ApiModelProperty(value = "图片描述", example = "图片仅供参考")
    private String pictureDesc;
}

