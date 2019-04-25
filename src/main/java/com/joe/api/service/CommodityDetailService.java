package com.joe.api.service;

import com.joe.api.dao.CommodityDetailMapper;
import com.joe.api.po.CommodityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityDetailService {

    @Autowired
    private CommodityDetailMapper commodityDetailMapper;

    //新增商品明细
    public int addCommodityDetail(CommodityDetail commodityDetail) {

        commodityDetail.setEnable(true);
        commodityDetailMapper.insertSelective(commodityDetail);

        return commodityDetail.getDetailId();
    }


    //修改商品明细（全部修改）
    public int modifyCommodityDetail(CommodityDetail commodityDetail) {

        return commodityDetailMapper.updateByCommodityId(commodityDetail);
    }


    // 根据商品id查询商品明细
    public CommodityDetail queryCommodityDetailByCommodityId(Integer commodityId) {

        return commodityDetailMapper.selectByCommodityId(commodityId);
    }


}
