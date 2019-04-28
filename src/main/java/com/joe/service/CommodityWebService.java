package com.joe.service;

import com.github.pagehelper.PageInfo;
import com.joe.api.enums.PictureTypeEnum;
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
import com.joe.dto.ApiPageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

        List<CommodityPicture> commodityPictures = new ArrayList<>();
        List<CommodityPicture> detailPictures = change2CommodityPictureList(commodityParam.getDetailPictures(), commodityId, PictureTypeEnum.COMMODITY_DETAIL.getCode());
        List<CommodityPicture> bannerPictures = change2CommodityPictureList(commodityParam.getBannerPictures(), commodityId, PictureTypeEnum.COMMODITY_BANNER.getCode());
        commodityPictures.addAll(detailPictures);
        commodityPictures.addAll(bannerPictures);
        commodityPictureService.addCommodityBatch(commodityPictures);

        return commodityId;
    }

    private List<CommodityPicture> change2CommodityPictureList(List<CommodityPictureVo> detailPictures, Integer commodityId, Integer pictureType) {
        return detailPictures.stream().map(picture -> {
            CommodityPicture commodityPicture = new CommodityPicture();
            BeanUtils.copyProperties(picture, commodityPicture);
            commodityPicture.setCommodityId(commodityId);
            commodityPicture.setPictureType(pictureType);
            commodityPicture.setCreateTime(new Date());
            commodityPicture.setUpdateTime(new Date());
            commodityPicture.setEnable(true);
            return commodityPicture;
        }).collect(Collectors.toList());
    }


    //查询某个类目下商品 分页
    public ApiPageResult queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        PageInfo<Commodity> pageInfo = commodityService.queryCommodityByItemId(itemId, pageNo, pageSize);

        if (CollectionUtils.isEmpty(pageInfo.getList())) {
            return new ApiPageResult(0L, new ArrayList<>());
        }

        List<CommodityVo> commodityVos = pageInfo.getList().stream().map(commodity -> {
            CommodityVo commodityVo = new CommodityVo();
            BeanUtils.copyProperties(commodity, commodityVo);
            return commodityVo;
        }).collect(Collectors.toList());

        return new ApiPageResult(pageInfo.getTotal(), commodityVos);
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
        List<CommodityPicture> pictureList = new ArrayList<>();
        List<CommodityPicture> detailPictures = change2CommodityPictureList(commodityParam.getDetailPictures(), commodityParam.getCommodityId(), PictureTypeEnum.COMMODITY_DETAIL.getCode());
        List<CommodityPicture> bannerPictures = change2CommodityPictureList(commodityParam.getBannerPictures(), commodityParam.getCommodityId(), PictureTypeEnum.COMMODITY_BANNER.getCode());
        pictureList.addAll(detailPictures);
        pictureList.addAll(bannerPictures);
        Integer k = commodityPictureService.addCommodityBatch(pictureList);

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
        List<CommodityPictureVo> detailPictures = new ArrayList<>();
        List<CommodityPictureVo> bannerPictures = new ArrayList<>();
        pictureList.stream().forEach(picture -> {
            CommodityPictureVo pictureVo = new CommodityPictureVo();
            BeanUtils.copyProperties(picture, pictureVo);
            if (PictureTypeEnum.COMMODITY_DETAIL.getCode().equals(picture.getPictureType())) {
                detailPictures.add(pictureVo);
            } else if (PictureTypeEnum.COMMODITY_BANNER.getCode().equals(picture.getPictureType())) {
                bannerPictures.add(pictureVo);
            }
        });
        commodityDetailVO.setDetailPictures(detailPictures);
        commodityDetailVO.setBannerPictures(bannerPictures);
        log.info("查询商品图片表完成，商品图片信息：{}", pictureList);

        return commodityDetailVO;
    }


}
