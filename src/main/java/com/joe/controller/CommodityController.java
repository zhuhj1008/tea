package com.joe.controller;

import com.joe.common.ApiParam;
import com.joe.common.ApiResult;
import com.joe.dto.commodity.CommodityCommonParam;
import com.joe.dto.commodity.CommodityDetailVO;
import com.joe.dto.commodity.CommodityParam;
import com.joe.dto.commodity.CommodityVo;
import com.joe.service.CommodityDetailWebService;
import com.joe.service.CommodityWebService;
import com.joe.util.mvc.ResponsePageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品管理
 */
@Slf4j
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityWebService commodityWebService;

    @Autowired
    private CommodityDetailWebService commodityDetailWebService;


    @RequestMapping("/addCommodity")
    public ApiResult addCommodity(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam commodityParam = apiParam.getBody();

        log.info("添加商品，商品名称：{}。", commodityParam.getCommodityName());
        int commodityId = commodityWebService.addCommodity(commodityParam);
        log.info("添加商品成功，商品编号：{}。", commodityId);

        return ApiResult.getSuccessEntity(commodityId);
    }


    /**
     * 根据类目id查询商品集合
     */
    @RequestMapping("/getCommodityListByItemId")
    public ApiResult getCommodityListByItemId(@RequestBody ApiParam<CommodityCommonParam> apiParam) {

        CommodityCommonParam param = apiParam.getBody();
        Integer itemId = param.getItemId();
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        log.info("查询商品列表，商品类目：{}，页码-大小：{}-{}。", itemId, pageNo, pageSize);

        int total = commodityWebService.queryCommodityCountByItemId(itemId);
        List<CommodityVo> commodityVoList = commodityWebService.queryCommodityByItemId(itemId, pageNo, pageSize);
        log.info("查询商品列表成功。");

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(commodityVoList);

        return ApiResult.getSuccessEntity(pageEntity);
    }


    /**
     * 获取推荐商品
     */
    @RequestMapping("/getRecommendCommodity")
    public ApiResult getRecommendCommodity() {

        log.info("查询推荐商品。");
        List<CommodityVo> commodityVoList = commodityWebService.queryRecommendCommodity();
        log.info("查询推荐商品成功。");

        return ApiResult.getSuccessEntity(commodityVoList);
    }


    /**
     * 切换商品推荐/不推荐
     */
    @RequestMapping("/switchCommodityRecommend")
    public ApiResult switchCommodityRecommend(@RequestBody ApiParam<CommodityCommonParam> apiParam) {

        CommodityCommonParam param = apiParam.getBody();

        Integer commodityId = param.getCommodityId();
        log.info("修改商品首页推荐状态，商品编号：{}。", commodityId);

        int executeNum = commodityWebService.updateRecommendStatus(commodityId);

        log.info("修改商品首页推荐状态成功。");
        return ApiResult.getSuccessEntity(executeNum);

    }


    /**
     * 删除商品
     */
    @RequestMapping("/removeCommodity")
    public ApiResult removeCommodity(@RequestBody ApiParam<CommodityCommonParam> apiParam) {

        CommodityCommonParam param = apiParam.getBody();
        Integer commodityId = param.getCommodityId();
        log.info("删除商品，商品编号：{}。", commodityId);

        int executeNum = commodityWebService.removeCommodity(commodityId);
        return ApiResult.getSuccessEntity(executeNum);
    }


    /**
     * 修改商品
     */
    @RequestMapping("/updateCommodity")
    public ApiResult updateCommodity(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam commodityParam = apiParam.getBody();
        log.info("修改商品，商品编号：{}", commodityParam.getCommodityId());
        int executeNum = commodityWebService.updateCommodity(commodityParam);

        if (executeNum == 0) {
            log.info("修改商品失败。");
            return ApiResult.getFailEntity();
        }

        log.info("修改商品成功。");
        return ApiResult.getSuccessEntity(executeNum);
    }


    @RequestMapping("/getDetailById")
    public ApiResult getCommodityDetailByCommodityId(@RequestBody ApiParam<CommodityCommonParam> apiParam) {

        CommodityCommonParam param = apiParam.getBody();
        Integer commodityId = param.getCommodityId();

        log.info("查询商品详情，商品编号：{}。", commodityId);
        CommodityDetailVO commodityDetailVO = commodityDetailWebService.queryDetailByCommodityId(commodityId);
        if (commodityDetailVO == null) {
            log.error("查询商品详情，没有找到对应商品，商品编号：{}。", commodityId);
            return ApiResult.getFailEntity("商品信息缺失");
        }

        return ApiResult.getSuccessEntity(commodityDetailVO);
    }

}
