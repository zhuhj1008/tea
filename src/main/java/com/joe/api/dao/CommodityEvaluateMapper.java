package com.joe.api.dao;

import com.joe.api.po.CommodityEvaluate;

public interface CommodityEvaluateMapper {
    int deleteByPrimaryKey(Integer evaluateId);

    int insertSelective(CommodityEvaluate record);

    CommodityEvaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(CommodityEvaluate record);

}