package com.joe.bussiness.commodity.service;

import com.joe.api.po.CommodityDetail;
import com.joe.api.service.CommodityDetailService;
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


    public CommodityDetailVO queryCommodityDetailByCommodityId(int commodityId){

        CommodityDetail commodityDetail = commodityDetailService.queryCommodityDetailByCommodityId(commodityId);

        return CommodityDetailVO.commodityDetail2DetailVo(commodityDetail);
    }
}
