package com.joe.api.dao;

import com.joe.api.po.GoodsItem;

public interface GoodsItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(GoodsItem record);

    int insertSelective(GoodsItem record);

    GoodsItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(GoodsItem record);

    int updateByPrimaryKey(GoodsItem record);
}