package com.joe.api.dao;

import com.joe.api.po.CommodityEvaluate;

import java.util.List;

public interface CommodityEvaluateMapper {

    int deleteByPrimaryKey(Integer evaluateId);

    int insertSelective(CommodityEvaluate record);

    CommodityEvaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(CommodityEvaluate record);

    int selectEvaluateCountByCommodityId(Integer commodityId);

    List<CommodityEvaluate> selectEvaluateByCommodityId(Integer commodityId);

}