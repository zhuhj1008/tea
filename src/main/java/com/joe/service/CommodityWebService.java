package com.joe.service;

import com.joe.api.dao.CommodityPictureMapper;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.po.CommodityPicture;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityPictureService;
import com.joe.api.service.CommodityService;
import com.joe.dto.commodity.CommodityParam;
import com.joe.dto.commodity.CommodityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品Service
 */
@Service
public class CommodityWebService {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityDetailService commodityDetailService;

    @Autowired
    private CommodityPictureService commodityPictureService;


    //新增商品
    public int addCommodity(CommodityParam commodityParam) {

        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityParam, commodity);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = new CommodityDetail();
        BeanUtils.copyProperties(commodityParam, commodityDetail);
        commodityDetail.setCommodityId(commodityId);
        commodityDetailService.addCommodityDetail(commodityDetail);

        List<CommodityPicture> commodityPictures = change2CommodityPictureList(commodityParam.getDetailPictures());
        commodityPictureService.addCommodityBatch(commodityPictures);

        return commodityId;
    }

    private List<CommodityPicture> change2CommodityPictureList(List<CommodityParam.Picture> detailPictures) {
        return detailPictures.stream().map(picture -> {
            CommodityPicture commodityPicture = new CommodityPicture();
            BeanUtils.copyProperties(picture, commodityPicture);
            return commodityPicture;
        }).collect(Collectors.toList());
    }


    //查询某个类目下商品 分页
    public List<CommodityVo> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        List<Commodity> commodities = commodityService.queryCommodityByItemId(itemId, pageNo, pageSize);
        return commodities.stream().map(commodity -> {
            CommodityVo commodityVo = new CommodityVo();
            BeanUtils.copyProperties(commodities, commodityVo);
            return commodityVo;
        }).collect(Collectors.toList());
    }


    //查询某个类目下商品数量
    public int queryCommodityCountByItemId(int itemId) {

        return commodityService.queryCommodityCountByItemId(itemId);
    }


    //删除商品
    public int removeCommodity(int commodityId) {

        return commodityService.dropCommodity(commodityId);
    }


    //修改商品 全部字段
    public int updateCommodity(CommodityParam commodityParam) {

        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityParam, commodity);
        int i = commodityService.modifyCommodityById(commodity);

        CommodityDetail commodityDetail = new CommodityDetail();
        BeanUtils.copyProperties(commodityParam, commodityDetail);
        int j = commodityDetailService.modifyCommodityDetail(commodityDetail);

        commodityPictureService.deleteByCommodityId(commodityParam.getCommodityId());
        List<CommodityPicture> commodityPictures = change2CommodityPictureList(commodityParam.getDetailPictures());
        Integer k = commodityPictureService.addCommodityBatch(commodityPictures);

        return i + j + k;
    }


    //获取推荐商品
    public List<CommodityVo> queryRecommendCommodity() {

        List<Commodity> commodities = commodityService.queryRecommendCommodity();

        return commodities.stream().map(commodity -> {
            CommodityVo commodityVo = new CommodityVo();
            BeanUtils.copyProperties(commodities, commodityVo);
            return commodityVo;
        }).collect(Collectors.toList());
    }


    //切换商品推荐状态
    public int updateRecommendStatus(int commodityId) {

        return commodityService.updateRecommendStatusById(commodityId);
    }


}
