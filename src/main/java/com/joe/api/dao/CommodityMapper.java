package com.joe.api.dao;

import com.joe.api.po.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    int insertSelective(Commodity record);

    int deleteByPrimaryKey(Integer commodityId);

    int updateByPrimaryKeySelective(Commodity record);

    Commodity selectByPrimaryKey(Integer commodityId);

    List<Commodity> selectByItemId(Integer itemId);

    List<Commodity> selectCommodityByItemIds(@Param(value = "itemList") List<Integer> itemList);

}