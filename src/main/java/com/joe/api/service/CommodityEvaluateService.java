package com.joe.api.service;

import com.github.pagehelper.PageHelper;
import com.joe.api.dao.CommodityEvaluateMapper;
import com.joe.api.po.CommodityEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommodityEvaluateService {

    @Autowired
    private CommodityEvaluateMapper commodityEvaluateMapper;

    /**
     * 新增评价
     *
     * @param commodityEvaluate 评价
     * @return 自增主键
     */
    public int addCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        commodityEvaluate.setEvaluateTime(new Date());
        commodityEvaluate.setCreateTime(new Date());
        commodityEvaluate.setUpdateTime(new Date());
        commodityEvaluate.setEnable(true);
        commodityEvaluateMapper.insertSelective(commodityEvaluate);

        return commodityEvaluate.getEvaluateId();
    }

    /**
     * 修改评价（追评）
     *
     * @param commodityEvaluate 评价
     * @return 修改记录数
     */
    public int updateCommodityEvaluate(CommodityEvaluate commodityEvaluate) {

        commodityEvaluate.setUpdateTime(new Date());
        return commodityEvaluateMapper.updateByPrimaryKeySelective(commodityEvaluate);

    }

    /**
     * 查询商品评价数量
     *
     * @param commodityId 商品编号
     * @return
     */
    public int queryEvaluateCountByCommodityId(Integer commodityId) {

        return commodityEvaluateMapper.selectEvaluateCountByCommodityId(commodityId);
    }

    /**
     * 查询商品评价列表 （分页）
     *
     * @param commodityId
     * @return
     */
    public List<CommodityEvaluate> queryEvaluateByCommodityId(Integer commodityId, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);

        return commodityEvaluateMapper.selectEvaluateByCommodityId(commodityId);
    }


}
