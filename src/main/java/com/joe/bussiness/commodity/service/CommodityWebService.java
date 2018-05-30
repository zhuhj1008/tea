package com.joe.bussiness.commodity.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.po.CommodityItem;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityItemService;
import com.joe.api.service.CommodityService;
import com.joe.bussiness.commodity.vo.CommodityDetailVO;
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


    public int addCommodity(CommodityVo commodityVo) {

        Commodity commodity = commodityVo.commodityVoToCommodity(commodityVo);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = commodityVo.commodityVoToCommodityDetail(commodityVo);
        commodityDetail.setCommodityId(commodityId);
        commodityDetailService.addCommodityDetail(commodityDetail);

        return commodityId;

    }


    public List<Commodity> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        return commodityService.queryCommodityByItemId(itemId, pageNo, pageSize);

    }

    public int queryCommodityPageCountByItemId(int itemId, int pageSize) {

        int count = commodityService.queryCommodityCountByItemId(itemId);

        return count / pageSize + 1;
    }



    public void removeCommodity(int commodityId) {

        commodityService.dropCommodity(commodityId);
    }
}
