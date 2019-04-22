package com.joe.dto.commodity;

import lombok.Data;

@Data
public class CommodityParam {

    private Integer itemId;

    private Integer pageNo;

    private Integer pageSize;

    private Integer commodityId;
}
