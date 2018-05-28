package com.joe.bussiness.commodity.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.po.CommodityItem;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityItemService;
import com.joe.api.service.CommodityService;
import com.joe.bussiness.commodity.vo.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CommodityWebService {

    @Autowired
    CommodityService commodityService;

    @Autowired
    CommodityDetailService commodityDetailService;

    @Autowired
    CommodityItemService commodityItemService;



    public int addCommodity(CommodityVo commodityVo){

        Commodity commodity = commodityVo.commodityVoToCommodity(commodityVo);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = commodityVo.commodityVoToCommodityDetail(commodityVo);
        commodityDetail.setCommodityId(commodityId);
        commodityDetailService.addCommodityDetail(commodityDetail);

        return commodityId;

    }

    public CommodityItem getItemById(int itemId){

        return  commodityItemService.queryCommodityItemById(itemId);
    }
}
