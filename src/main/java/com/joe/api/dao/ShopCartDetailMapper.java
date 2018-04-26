package com.joe.api.dao;

import com.joe.api.po.ShopCartDetail;

public interface ShopCartDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(ShopCartDetail record);

    int insertSelective(ShopCartDetail record);

    ShopCartDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(ShopCartDetail record);

    int updateByPrimaryKey(ShopCartDetail record);
}