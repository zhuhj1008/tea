package com.joe.service;

import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.api.po.CommodityPicture;
import com.joe.api.service.CommodityDetailService;
import com.joe.api.service.CommodityPictureService;
import com.joe.api.service.CommodityService;
import com.joe.dto.commodity.CommodityDetailVO;
import com.joe.dto.commodity.CommodityParam;
import com.joe.dto.commodity.CommodityPictureVo;
import com.joe.dto.commodity.CommodityVo;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品Service
 */
@Slf4j
@Service
public class CommodityWebService {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityDetailService commodityDetailService;

    @Autowired
    private CommodityPictureService commodityPictureService;


    //新增商品
    @Transactional
    public int addCommodity(CommodityParam commodityParam) {

        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityParam, commodity);
        int commodityId = commodityService.addCommodity(commodity);

        CommodityDetail commodityDetail = new CommodityDetail();
        BeanUtils.copyProperties(commodityParam, commodityDetail);
        commodityDetail.setCommodityId(commodityId);
        commodityDetailService.addCommodityDetail(commodityDetail);

        List<CommodityPicture> commodityPictures = change2CommodityPictureList(commodityParam.getDetailPictures(), commodityId);
        commodityPictureService.addCommodityBatch(commodityPictures);

        return commodityId;
    }

    private List<CommodityPicture> change2CommodityPictureList(List<CommodityPictureVo> detailPictures, Integer commodityId) {
        return detailPictures.stream().map(picture -> {
            CommodityPicture commodityPicture = new CommodityPicture();
            BeanUtils.copyProperties(picture, commodityPicture);
            commodityPicture.setCommodityId(commodityId);
            commodityPicture.setCreateTime(new Date());
            commodityPicture.setUpdateTime(new Date());
            commodityPicture.setEnable(true);
            return commodityPicture;
        }).collect(Collectors.toList());
    }


    //查询某个类目下商品 分页
    public List<CommodityVo> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        List<Commodity> commodities = commodityService.queryCommodityByItemId(itemId, pageNo, pageSize);
        return commodities.stream().map(commodity -> {
            CommodityVo commodityVo = new CommodityVo();
            BeanUtils.copyProperties(commodity, commodityVo);
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
        List<CommodityPicture> commodityPictures = change2CommodityPictureList(commodityParam.getDetailPictures(), commodityParam.getCommodityId());
        Integer k = commodityPictureService.addCommodityBatch(commodityPictures);

        return i + j + k;
    }


    //获取推荐商品
    public List<CommodityVo> queryRecommendCommodity() {

        List<Commodity> commodities = commodityService.queryRecommendCommodity();

        return commodities.stream().map(commodity -> {
            CommodityVo commodityVo = new CommodityVo();
            BeanUtils.copyProperties(commodity, commodityVo);
            return commodityVo;
        }).collect(Collectors.toList());
    }


    //切换商品推荐状态
    public int updateRecommendStatus(int commodityId) {

        return commodityService.updateRecommendStatusById(commodityId);
    }


    //查询商品明细
    public CommodityDetailVO queryDetailByCommodityId(int commodityId) {

        CommodityDetailVO commodityDetailVO = new CommodityDetailVO();

        Commodity commodity = commodityService.queryCommodityById(commodityId);
        BeanUtils.copyProperties(commodity, commodityDetailVO);
        log.info("查询商品主表完成，商品信息：{}", commodity);

        CommodityDetail commodityDetail = commodityDetailService.queryCommodityDetailByCommodityId(commodityId);
        BeanUtils.copyProperties(commodityDetail, commodityDetailVO);
        log.info("查询商品详情表完成，商品详细信息：{}", commodityDetail);


        List<CommodityPicture> pictureList = commodityPictureService.findByCommodityId(commodityId);
        List<CommodityPictureVo> pictureVoList = pictureList.stream().map(picture -> {
            CommodityPictureVo pictureVo = new CommodityPictureVo();
            BeanUtils.copyProperties(picture, pictureVo);
            return pictureVo;
        }).collect(Collectors.toList());
        commodityDetailVO.setDetailPictures(pictureVoList);
        log.info("查询商品图片表完成，商品图片信息：{}", pictureList);

        return commodityDetailVO;
    }


}
