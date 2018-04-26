package com.joe.api.dao;

import com.joe.api.po.CommodityDetail;

public interface CommodityDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(CommodityDetail record);

    int insertSelective(CommodityDetail record);

    CommodityDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(CommodityDetail record);

    int updateByPrimaryKey(CommodityDetail record);
}