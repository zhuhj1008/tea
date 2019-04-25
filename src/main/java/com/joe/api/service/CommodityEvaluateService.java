package com.joe.api.service;

import com.github.pagehelper.PageHelper;
import com.joe.api.dao.CommodityEvaluateMapper;
import com.joe.api.po.CommodityEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommodityEvaluateService {

    @Autowired
    private CommodityEvaluateMapper commodityEvaluateMapper;

   //新增评价
    public int addCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        commodityEvaluate.setEvaluateTime(new Date());
        commodityEvaluate.setEnable(true);
        commodityEvaluateMapper.insertSelective(commodityEvaluate);

        return commodityEvaluate.getEvaluateId();
    }

   //修改评价
    public int updateCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        return commodityEvaluateMapper.updateByPrimaryKeySelective(commodityEvaluate);
    }

    //查询商品评价数量
    public int queryEvaluateCountByCommodityId(Integer commodityId) {

        return commodityEvaluateMapper.selectEvaluateCountByCommodityId(commodityId);
    }

    //查询商品评价数量
    public List<CommodityEvaluate> queryEvaluateByCommodityId(Integer commodityId, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        return commodityEvaluateMapper.selectEvaluateByCommodityId(commodityId);
    }


}
