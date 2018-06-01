package com.joe.api.dao;

import com.joe.api.po.CommodityDetail;

public interface CommodityDetailMapper {

    int insertSelective(CommodityDetail record);

    int deleteByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(CommodityDetail record);

    int updateByCommodityIdSelective(CommodityDetail commodityDetail);

    int updateByCommodityId(CommodityDetail commodityDetail);

    CommodityDetail selectByPrimaryKey(Integer detailId);

    CommodityDetail selectByCommodityId(Integer commodityId);
}