package com.joe.bussiness.commodity.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityService;
import com.joe.bussiness.base.BaseService;
import com.joe.bussiness.commodity.vo.CommodityDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by  on 2018-05-29 12:21
 **/
@Service
public class CommodityDetailWebService extends BaseService{

    @Autowired
    CommodityDetailService commodityDetailService;

    @Autowired
    CommodityService commodityService;


    public CommodityDetailVO queryCommodityDetailByCommodityId(int commodityId){

        CommodityDetailVO commodityDetailVO = new CommodityDetailVO();

        CommodityDetail commodityDetail = commodityDetailService.queryCommodityDetailByCommodityId(commodityId);
        Commodity commodity = commodityService.queryCommodityById(commodityId);

        if(commodityDetail == null || commodity == null){
            return null;
        }

        CommodityDetailVO.commodityDetail2DetailVo(commodityDetailVO,commodityDetail);
        CommodityDetailVO.commodity2DetailVo(commodityDetailVO,commodity);

        return commodityDetailVO;
    }
}
