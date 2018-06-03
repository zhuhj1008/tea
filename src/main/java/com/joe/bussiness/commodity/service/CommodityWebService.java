package com.joe.bussiness.commodity.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.po.CommodityItem;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityItemService;
import com.joe.api.service.CommodityService;
import com.joe.bussiness.commodity.vo.CommodityDetailVO;
import com.joe.bussiness.commodity.vo.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CommodityWebService {

    @Autowired
    CommodityService commodityService;

    @Autowired
    CommodityDetailService commodityDetailService;

    @Autowired
    CommodityItemService commodityItemService;


    //新增商品
    public int addCommodity(CommodityVo commodityVo) {

        Commodity commodity = commodityVo.commodityVoToCommodity(commodityVo);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = commodityVo.commodityVoToCommodityDetail(commodityVo);
        commodityDetail.setCommodityId(commodityId);
        commodityDetailService.addCommodityDetail(commodityDetail);

        return commodityId;
    }

    //查询某个类目下商品 分页
    public List<Commodity> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        return commodityService.queryCommodityByItemId(itemId, pageNo, pageSize);
    }

    //查询某个类目下商品数量
    public int queryCommodityCountByItemId(int itemId) {

        return commodityService.queryCommodityCountByItemId(itemId);
    }

    //删除商品
    public void removeCommodity(int commodityId) {

        commodityService.dropCommodity(commodityId);
    }

    //修改商品 全部字段
    public void updateCommodity(CommodityVo commodityVo) {

        commodityService.modifyCommodityById(commodityVo.commodityVoToCommodity(commodityVo));
        commodityDetailService.modifyCommodityDetail(commodityVo.commodityVoToCommodityDetail(commodityVo));
    }

    //获取推荐商品
    public List<Commodity> queryRecommendCommodity() {

        return commodityService.queryRecommendCommodity();
    }

    //切换商品推荐状态
    public int updateRecommendStatus(int commodityId) {

        return commodityService.updateRecommendStatusById(commodityId);
    }
}
