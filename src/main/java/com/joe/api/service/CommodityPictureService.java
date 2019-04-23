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


    public Integer addCommodity(CommodityPicture commodityPicture) {

        if (commodityPicture == null) {
            return null;
        }
        commodityPictureMapper.addCommodityPicture(commodityPicture);
        return commodityPicture.getPictureId();
    }


    public Integer addCommodityBatch(List<CommodityPicture> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }

        return commodityPictureMapper.addCommodityPictureBatch(list);
    }

    public Integer updateCommodityPicture(CommodityPicture commodityPicture){

        if(commodityPicture == null || commodityPicture.getPictureId()==null){
            return 0;
        }
        return commodityPictureMapper.updateByPrimaryKeySelective(commodityPicture);
    }

    public Integer deleteByCommodityId(Integer commodityId){

        return commodityPictureMapper.deleteByCommodityId(commodityId);
    }
}
