package com.joe.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityService;
import com.joe.dto.commodity.CommodityDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品明细Service
 * create by  on 2018-05-29 12:21
 **/
@Service
public class CommodityDetailWebService {

    @Autowired
    private CommodityDetailService commodityDetailService;

    @Autowired
    private CommodityService commodityService;


    //查询商品明细
    public CommodityDetailVO queryDetailByCommodityId(int commodityId) {

        CommodityDetailVO commodityDetailVO = new CommodityDetailVO();

        CommodityDetail commodityDetail = commodityDetailService.queryCommodityDetailByCommodityId(commodityId);
        Commodity commodity = commodityService.queryCommodityById(commodityId);

        if (commodityDetail == null || commodity == null) {
            return null;
        }

        CommodityDetailVO.commodityDetail2DetailVo(commodityDetailVO, commodityDetail);
        CommodityDetailVO.commodity2DetailVo(commodityDetailVO, commodity);

        return commodityDetailVO;
    }
}
