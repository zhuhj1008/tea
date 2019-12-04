package com.joe.api.dao;

import com.joe.api.po.Commodity;
import com.joe.dto.commodity.CommodityVo;

import java.util.List;

public interface CommodityMapper {

    int insertSelective(Commodity record);

    int deleteByPrimaryKey(Integer commodityId);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    int updateRecommendStatusById(int commodityId);

    Commodity selectByPrimaryKey(Integer commodityId);

    List<Commodity> selectByItemId(Integer itemId);

    int selectCommodityCountByItemId(Integer itemId);

    List<CommodityVo> selectRecommendCommodity();

}