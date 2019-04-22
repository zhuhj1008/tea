package com.joe.service;

import com.joe.api.po.CommodityEvaluate;
import com.joe.api.service.CommodityEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品评价业务
 * create by Joe on 2018-06-03 13:57
 **/
@Service
public class CommodityEvaluateWebService {

    @Autowired
    private CommodityEvaluateService commodityEvaluateService;


    //新增商品评价
    public int addCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        return commodityEvaluateService.addCommodityEvaluate(commodityEvaluate);
    }

    //追加商品评价
    public int appendCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        commodityEvaluate.setAppendEvaluateTime(new Date());
        return commodityEvaluateService.updateCommodityEvaluate(commodityEvaluate);
    }

    //查询商品的评价数量
    public int queryEvaluateCountByCommodityId(Integer commodityId) {

        return commodityEvaluateService.queryEvaluateCountByCommodityId(commodityId);
    }

    //查询评价列表
    public List<CommodityEvaluate> queryEvaluateByCommodityId(Integer commodityId, Integer pageNo, Integer pageSize) {

        return commodityEvaluateService.queryEvaluateByCommodityId(commodityId, pageNo, pageSize);
    }


}
