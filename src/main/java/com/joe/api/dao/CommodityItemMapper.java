package com.joe.api.dao;

import com.joe.api.po.CommodityItem;

import java.util.List;

public interface CommodityItemMapper {

    int insertSelective(CommodityItem record);

    int updateByPrimaryKeySelective(CommodityItem record);

    CommodityItem selectByItemId(Integer itemId);

    CommodityItem selectByItemName(String name);

    List<Integer> selectSubItemIdList(Integer itemId);
}