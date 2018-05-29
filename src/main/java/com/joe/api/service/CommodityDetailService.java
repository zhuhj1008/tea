package com.joe.api.service;

import com.joe.api.dao.CommodityDetailMapper;
import com.joe.api.po.CommodityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityDetailService {

    @Autowired
    CommodityDetailMapper commodityDetailMapper;

    /**
     * 新增商品明细
     *
     * @param commodityDetail 商品明细
     * @return 自增主键
     */
    public int addCommodityDetail(CommodityDetail commodityDetail) {

        commodityDetail.setEnable(true);
        commodityDetailMapper.insertSelective(commodityDetail);

        return commodityDetail.getDetailId();
    }

    /**
     * 删除商品明细By 商品ID
     *
     * @return
     */
    public int dropCommodityDeatil(int commodityDetailId) {

        CommodityDetail commodityDetail = new CommodityDetail();
        commodityDetail.setDetailId(commodityDetailId);
        commodityDetail.setEnable(false);

        return commodityDetailMapper.updateByPrimaryKeySelective(commodityDetail);

    }

    /**
     * 修改商品明细By 商品Id
     *
     * @param commodityDetail
     * @return 修改记录数
     */
    public int modifyCommodityDetail(CommodityDetail commodityDetail) {

        return commodityDetailMapper.updateByCommodityIdSelective(commodityDetail);
    }

    /**
     * 根据商品id查询商品明细
     * @param commodityId
     * @return
     */
    public CommodityDetail queryCommodityDetailByCommodityId(Integer commodityId){
        return commodityDetailMapper.selectByCommodityId(commodityId);
    }


}
