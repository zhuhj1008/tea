package com.joe.bussiness.commodity.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityService;
import com.joe.bussiness.commodity.vo.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CommodityWebService {

    @Autowired
    CommodityService commodityService;

    @Autowired
    CommodityDetailService commodityDetailService;



    public void addCommodity(CommodityVo commodityVo){
        Commodity commodity = new Commodity();
        commodity.setCommodityName(commodityVo.getName());
        commodity.setItemId(commodityVo.getItem());
        commodity.setPicture(commodityVo.getPhoto());
        commodity.setCreateTime(new Date());
        commodity.setCreateBy(1);
        commodity.setEnable(true);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = new CommodityDetail();
        commodityDetail.setCommodityId(commodityId);
        commodityDetail.setIntegral(commodityVo.getStock());//库存
        commodityDetail.setUnit("100g");
        commodityDetail.setFreight(new BigDecimal(0.00));
        commodityDetailService.addCommodityDetail(commodityDetail);
    }

    public void getAllCommodityItem(){

    }
}
