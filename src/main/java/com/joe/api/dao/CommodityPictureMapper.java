package com.joe.api.dao;

import com.joe.api.po.CommodityPicture;

import java.util.List;

public interface CommodityPictureMapper {

    int addCommodityPicture(CommodityPicture record);

    int addCommodityPictureBatch(List<CommodityPicture> records);

    CommodityPicture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(CommodityPicture record);

    int deleteByCommodityId(Integer commodityId);
}