package com.joe.api.service;

import com.joe.api.dao.CommodityPictureMapper;
import com.joe.api.po.CommodityPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CommodityPictureService {

    @Autowired
    private CommodityPictureMapper commodityPictureMapper;

    //批量保存图片
    public Integer addCommodityBatch(List<CommodityPicture> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }

        return commodityPictureMapper.addCommodityPictureBatch(list);
    }

    //根据商品编号删除图片
    public Integer deleteByCommodityId(Integer commodityId) {

        return commodityPictureMapper.deleteByCommodityId(commodityId);
    }

    //根据商品编号查询图片
    public List<CommodityPicture> findByCommodityId(Integer commodityId) {
        return commodityPictureMapper.findByCommodityId(commodityId);
    }
}
