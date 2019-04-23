package com.joe.api.dao;

import com.joe.api.po.CommodityPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityPictureMapper {

    int addCommodityPicture(CommodityPicture record);

    int addCommodityPictureBatch(@Param(value = "records")  List<CommodityPicture> records);

    CommodityPicture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(CommodityPicture record);

    int deleteByCommodityId(Integer commodityId);

    List<CommodityPicture> findByCommodityId(Integer commodityId);
}