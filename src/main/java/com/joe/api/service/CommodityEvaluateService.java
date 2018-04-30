package com.joe.api.service;

import com.joe.api.dao.CommodityEvaluateMapper;
import com.joe.api.po.CommodityEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommodityEvaluateService {

    @Autowired
    CommodityEvaluateMapper commodityEvaluateMapper;

    /**
     * 新增评价
     * @param commodityEvaluate 评价
     * @return 自增主键
     */
    public int addCommodityEvaluate(CommodityEvaluate commodityEvaluate){

        commodityEvaluate.setCreateTime(new Date());
        commodityEvaluate.setUpdateTime(new Date());
        commodityEvaluate.setEnable(true);
        commodityEvaluateMapper.insertSelective(commodityEvaluate);

        return commodityEvaluate.getEvaluateId();
    }

    /**
     * 修改评价（追评）
     * @param commodityEvaluate  评价
     * @return  修改记录数
     */
    public int updateCommodityEvaluate(CommodityEvaluate commodityEvaluate){

        commodityEvaluate.setUpdateTime(new Date());
        return commodityEvaluateMapper.updateByPrimaryKeySelective(commodityEvaluate);

    }
}
