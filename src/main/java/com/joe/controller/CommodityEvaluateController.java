package com.joe.controller;

import com.joe.api.po.CommodityEvaluate;
import com.joe.common.ApiParameter;
import com.joe.common.ApiResult;
import com.joe.dto.commodity.CommodityCommonParam;
import com.joe.dto.commodity.CommodityPageParam;
import com.joe.service.CommodityEvaluateWebService;
import com.joe.util.mvc.ResponsePageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品评价请求
 * create by Joe on 2018-06-03 13:48
 **/
@Slf4j
@Api(tags={"商品评价接口"})
@RestController
@RequestMapping("/evaluate")
public class CommodityEvaluateController {

    @Autowired
    private CommodityEvaluateWebService commodityEvaluateWebService;

    /**
     * 添加商品评价
     */
    @PostMapping("/addEvaluate")
    @ApiOperation(value = "添加商品评价", notes = "添加商品评价")
    public ApiResult addEvaluate(@RequestBody ApiParameter<CommodityEvaluate> apiParameter) {

        CommodityEvaluate param = apiParameter.getBody();
        log.info("添加商品评价，商品编号：{}。", param.getCommodityId());

        int evaluateId = commodityEvaluateWebService.addCommodityEvaluate(param);
        log.info("添加商品评价成功。", evaluateId);

        return ApiResult.getSuccessEntity(evaluateId);
    }


    /**
     * 追加商品评价
     */
    @PostMapping("/appendEvaluate")
    @ApiOperation(value = "追加商品评价", notes = "追加商品评价")
    public ApiResult appendEvaluate(@RequestBody ApiParameter<CommodityEvaluate> apiParameter) {

        CommodityEvaluate commodityEvaluate = apiParameter.getBody();
        log.info("追加商品评价，商品编号：{}。", commodityEvaluate.getEvaluateId());

        int executeNum = commodityEvaluateWebService.appendCommodityEvaluate(commodityEvaluate);
        if (executeNum > 0) {
            log.info("追加商品评价失败。");
            return ApiResult.getFailEntity();
        }
        log.info("追加商品评价成功。");

        return ApiResult.getSuccessEntity(executeNum);
    }


    /**
     * 查看商品评价（分页）
     */
    @PostMapping("/getEvaluate")
    @ApiOperation(value = "查看商品评价", notes = "分页查看商品评价")
    public ApiResult getEvaluateByCommodityId(@RequestBody ApiParameter<CommodityPageParam> apiParameter) {

        CommodityPageParam param = apiParameter.getBody();
        Integer commodityId = param.getCommodityId();
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        log.info("查看商品评价，商品编号：{}，页码-大小：{}-{}", commodityId, pageNo, pageSize);

        int total = commodityEvaluateWebService.queryEvaluateCountByCommodityId(commodityId);
        List<CommodityEvaluate> commodityEvaluates = commodityEvaluateWebService.queryEvaluateByCommodityId(commodityId, pageNo, pageSize);
        log.info("查询商品评价成功。");

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(commodityEvaluates);

        return ApiResult.getSuccessEntity(pageEntity);
    }


}
