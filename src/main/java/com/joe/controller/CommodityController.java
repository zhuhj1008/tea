package com.joe.controller;

import com.joe.common.ApiParameter;
import com.joe.common.ApiResult;
import com.joe.dto.commodity.*;
import com.joe.service.CommodityWebService;
import com.joe.util.mvc.ResponsePageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品管理
 */
@Slf4j
@RestController
@Api(tags = {"商品信息接口"})
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityWebService commodityWebService;


    @PostMapping("/addCommodity")
    @ApiOperation(value = "添加商品", notes = "添加商品")
    public ApiResult addCommodity(@RequestBody @ApiParam ApiParameter<CommodityParam> apiParameter) {

        CommodityParam commodityParam = apiParameter.getBody();

        log.info("添加商品，商品名称：{}。", commodityParam.getCommodityName());
        int commodityId = commodityWebService.addCommodity(commodityParam);
        log.info("添加商品成功，商品编号：{}。", commodityId);

        return ApiResult.getSuccessEntity(commodityId);
    }


    /**
     * 根据类目id查询商品集合
     */
    @PostMapping("/getCommodityListByItemId")
    @ApiOperation(value = "查询商品列表", notes = "根据类目编号分页查询商品列表")
    public ApiResult getCommodityListByItemId(@RequestBody @ApiParam ApiParameter<CommodityPageParam> apiParameter) {

        CommodityPageParam param = apiParameter.getBody();
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
    @PostMapping("/getRecommendCommodity")
    @ApiOperation(value = "查询推荐商品", notes = "查询首页推荐商品")
    public ApiResult getRecommendCommodity() {

        log.info("查询推荐商品。");
        List<CommodityVo> commodityVoList = commodityWebService.queryRecommendCommodity();
        log.info("查询推荐商品成功。");

        return ApiResult.getSuccessEntity(commodityVoList);
    }


    /**
     * 切换商品推荐/不推荐
     */
    @PostMapping("/switchCommodityRecommend")
    @ApiOperation(value = "切换商品推荐状态", notes = "切换商品推荐状态，推荐->不推荐  不推荐->推荐")
    public ApiResult switchCommodityRecommend(@RequestBody ApiParameter<CommodityCommonParam> apiParameter) {

        CommodityCommonParam param = apiParameter.getBody();

        Integer commodityId = param.getCommodityId();
        log.info("修改商品首页推荐状态，商品编号：{}。", commodityId);

        int executeNum = commodityWebService.updateRecommendStatus(commodityId);

        log.info("修改商品首页推荐状态成功。");
        return ApiResult.getSuccessEntity(executeNum);

    }


    /**
     * 删除商品
     */
    @PostMapping("/removeCommodity")
    @ApiOperation(value = "删除商品", notes = "逻辑删除商品和商品详情")
    public ApiResult removeCommodity(@RequestBody ApiParameter<CommodityCommonParam> apiParameter) {

        CommodityCommonParam param = apiParameter.getBody();
        Integer commodityId = param.getCommodityId();
        log.info("删除商品，商品编号：{}。", commodityId);

        int executeNum = commodityWebService.removeCommodity(commodityId);
        return ApiResult.getSuccessEntity(executeNum);
    }


    /**
     * 修改商品
     */
    @PostMapping("/updateCommodity")
    @ApiOperation(value = "修改商品信息", notes = "修改商品信息")
    public ApiResult updateCommodity(@RequestBody ApiParameter<CommodityParam> apiParameter) {

        CommodityParam commodityParam = apiParameter.getBody();
        log.info("修改商品，商品编号：{}", commodityParam.getCommodityId());
        int executeNum = commodityWebService.updateCommodity(commodityParam);

        if (executeNum == 0) {
            log.info("修改商品失败。");
            return ApiResult.getFailEntity();
        }

        log.info("修改商品成功。");
        return ApiResult.getSuccessEntity(executeNum);
    }


    @PostMapping("/getDetailById")
    @ApiOperation(value = "查询商品详细信息", notes = "根据商品编号查询商品详细信息")
    public ApiResult getCommodityDetailByCommodityId(@RequestBody ApiParameter<CommodityCommonParam> apiParameter) {

        CommodityCommonParam param = apiParameter.getBody();
        Integer commodityId = param.getCommodityId();

        log.info("查询商品详情，商品编号：{}。", commodityId);
        CommodityDetailVO commodityDetailVO = commodityWebService.queryDetailByCommodityId(commodityId);
        if (commodityDetailVO == null) {
            log.error("查询商品详情，没有找到对应商品，商品编号：{}。", commodityId);
            return ApiResult.getFailEntity("商品信息缺失");
        }
        log.info("查询商品详情成功。商品信息：{}。", commodityDetailVO);
        return ApiResult.getSuccessEntity(commodityDetailVO);
    }

}
