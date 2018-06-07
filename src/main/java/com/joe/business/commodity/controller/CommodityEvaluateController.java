package com.joe.business.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.CommodityEvaluate;
import com.joe.business.common.base.BaseController;
import com.joe.business.commodity.service.CommodityEvaluateWebService;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品评价请求
 * create by Joe on 2018-06-03 13:48
 **/
@Controller
@RequestMapping("/evaluate")
public class CommodityEvaluateController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityEvaluateController.class);

    @Autowired
    CommodityEvaluateWebService commodityEvaluateWebService;

    //新增商品评价
    @RequestMapping("/addEvaluate")
    @ResponseBody
    public Object addEvaluate(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityEvaluate commodityEvaluate = JSON.parseObject(requestParam, CommodityEvaluate.class);
        logger.info("新增商品评价，商品编号：{}", commodityEvaluate.getCommodityId());
        int primaryKey = commodityEvaluateWebService.addCommodityEvaluate(commodityEvaluate);
        logger.info("新增商品评价成功，评论编号：{}", primaryKey);

        return ResponseEntity.getSuccessEntity("新增商品成功",primaryKey);
    }


    //商品追加评价
    @RequestMapping("/appendEvaluate")
    @ResponseBody
    public Object appendEvaluate(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityEvaluate commodityEvaluate = JSON.parseObject(requestParam, CommodityEvaluate.class);

        int count = commodityEvaluateWebService.appendCommodityEvaluate(commodityEvaluate);

        if (count > 0) {
            return ResponseEntity.getSuccessEntity("添加成功", count);
        }

        return ResponseEntity.getFailEntity("添加失败");

    }

    //查看商品评价

    /**
     * 查看商品评价（分页）
     *
     * @param request
     * @return
     */
    @RequestMapping("/getEvaluate")
    @ResponseBody
    public Object getEvaluateByCommodityId(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("param error");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer commodityId = Integer.valueOf(jsonObject.get("commodityId").toString());
        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());
        logger.info("获取商品评价列表，商品编号：{}，页码：{}，每页大小：{}", commodityId, pageNo, pageSize);

        int total = commodityEvaluateWebService.queryEvaluateCountByCommodityId(commodityId);
        List<CommodityEvaluate> commodityEvaluates = commodityEvaluateWebService.queryEvaluateByCommodityId(commodityId, pageNo, pageSize);

        logger.info("查询商品列表结束");
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("contents", commodityEvaluates);

        return ResponseEntity.getSuccessEntity("查询商品成功",data);
    }


}
