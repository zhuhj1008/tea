package com.joe.business.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.CommodityEvaluate;
import com.joe.business.common.base.BaseController;
import com.joe.business.commodity.service.CommodityEvaluateWebService;
import com.joe.util.mvc.ResponseEntity;
import com.joe.util.mvc.ResponsePageEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品评价请求
 * create by Joe on 2018-06-03 13:48
 **/
@RestController
@RequestMapping("/evaluate")
public class CommodityEvaluateController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityEvaluateController.class);

    @Autowired
    CommodityEvaluateWebService commodityEvaluateWebService;

    /**
     * 添加商品评价
     */
    @RequestMapping("/addEvaluate")
    public Object addEvaluate(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityEvaluate commodityEvaluate = JSON.parseObject(requestParam, CommodityEvaluate.class);
        logger.info("request add commodity evaluate, commodity id is {}.", commodityEvaluate.getCommodityId());
        int evaluateId = commodityEvaluateWebService.addCommodityEvaluate(commodityEvaluate);
        logger.info("add commodity evaluate success, evaluate id is {}.", evaluateId);

        return ResponseEntity.getSuccessEntity("新增商品评价成功", evaluateId);
    }


    /**
     * 追加商品评价
     */
    @RequestMapping("/appendEvaluate")
    public Object appendEvaluate(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityEvaluate commodityEvaluate = JSON.parseObject(requestParam, CommodityEvaluate.class);
        logger.info("request append evaluate, evaluate id is {}", commodityEvaluate.getEvaluateId());

        int executeNum = commodityEvaluateWebService.appendCommodityEvaluate(commodityEvaluate);

        if (executeNum > 0) {
            logger.info("append evaluate fail.");
            return ResponseEntity.getFailEntity("追加评论失败");
        }

        logger.info("append evaluate success.");
        return ResponseEntity.getSuccessEntity("追加评论成功", executeNum);

    }


    /**
     * 查看商品评价（分页）
     */
    @RequestMapping("/getEvaluate")
    public Object getEvaluateByCommodityId(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer commodityId = Integer.valueOf(jsonObject.get("commodityId").toString());
        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());
        logger.info("request commodity evaluate list , param : commodity id is {}, pageNo is {}, pageSize is {}.", commodityId, pageNo, pageSize);

        int total = commodityEvaluateWebService.queryEvaluateCountByCommodityId(commodityId);
        List<CommodityEvaluate> commodityEvaluates = commodityEvaluateWebService.queryEvaluateByCommodityId(commodityId, pageNo, pageSize);

        logger.info("request commodity evaluate success.");

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(commodityEvaluates);

        return ResponseEntity.getSuccessEntity("查询商品评价成功", pageEntity);
    }


}
