package com.joe.controller;

import com.joe.api.po.CommodityEvaluate;
import com.joe.common.ApiParam;
import com.joe.common.ApiResult;
import com.joe.dto.commodity.CommodityParam;
import com.joe.service.CommodityEvaluateWebService;
import com.joe.util.mvc.ResponsePageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品评价请求
 * create by Joe on 2018-06-03 13:48
 **/
@Slf4j
@RestController
@RequestMapping("/evaluate")
public class CommodityEvaluateController {

    @Autowired
    private CommodityEvaluateWebService commodityEvaluateWebService;

    /**
     * 添加商品评价
     */
    @RequestMapping("/addEvaluate")
    public ApiResult addEvaluate(@RequestBody ApiParam<CommodityEvaluate> apiParam) {

        CommodityEvaluate param = apiParam.getBody();
        log.info("添加商品评价，商品编号：{}。", param.getCommodityId());

        int evaluateId = commodityEvaluateWebService.addCommodityEvaluate(param);
        log.info("添加商品评价成功。", evaluateId);

        return ApiResult.getSuccessEntity(evaluateId);
    }


    /**
     * 追加商品评价
     */
    @RequestMapping("/appendEvaluate")
    public ApiResult appendEvaluate(@RequestBody ApiParam<CommodityEvaluate> apiParam) {

        CommodityEvaluate commodityEvaluate = apiParam.getBody();
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
    @RequestMapping("/getEvaluate")
    public ApiResult getEvaluateByCommodityId(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam param = apiParam.getBody();
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
