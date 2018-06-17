package com.joe.business.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.business.common.base.BaseController;
import com.joe.business.commodity.service.CommodityWebService;
import com.joe.business.commodity.vo.CommodityVo;
import com.joe.util.mvc.ResponseEntity;
import com.joe.util.mvc.ResponsePageEntity;
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
 * 商品管理
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private CommodityWebService commodityWebService;


    @RequestMapping("/addCommodity")
    @ResponseBody
    public Object addCommodity(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityVo commodityVo = JSON.parseObject(requestParam, CommodityVo.class);

        logger.info("request add commodity, commodity name is {}.", commodityVo.getpName());
        int commodityId = commodityWebService.addCommodity(commodityVo);
        logger.info("add commodity success, commodity id is {}.", commodityId);

        return ResponseEntity.getSuccessEntity("新增商品成功", commodityId);
    }


    /**
     * 根据类目id查询商品集合
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCommodityListByItemId")
    @ResponseBody
    public Object getCommodityListByItemId(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数有误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer itemId = Integer.valueOf(jsonObject.get("itemId").toString());
        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());
        logger.info("request commodity list, param  item {}, pageNo {}, pagesSize{}.", itemId, pageNo, pageSize);

        int total = commodityWebService.queryCommodityCountByItemId(itemId);
        List<Commodity> commodityList = commodityWebService.queryCommodityByItemId(itemId, pageNo, pageSize);
        logger.info("request commodity list success.");

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(commodityList);

        return ResponseEntity.getSuccessEntity("查询商品列表成功", pageEntity);
    }


    /**
     * 获取推荐商品
     *
     * @return
     */
    @RequestMapping("/getRecommendCommodity")
    @ResponseBody
    public Object getRecommendCommodity() {

        logger.info("request recommend commodity list");
        List<Commodity> commodities = commodityWebService.queryRecommendCommodity();
        logger.info("request recommend commodity list success, total {}", commodities.size());

        return ResponseEntity.getSuccessEntity("查询推荐商品成功", commodities);
    }


    /**
     * 切换商品推荐/不推荐
     *
     * @param request
     * @return
     */
    @RequestMapping("/switchCommodityRecommend")
    @ResponseBody
    public Object switchCommodityRecommend(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("param error");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer commodityId = Integer.valueOf(jsonObject.get("commodityId").toString());
        logger.info("request change commodity recommend status, commodity id is {}.", commodityId);

        int executeNum = commodityWebService.updateRecommendStatus(commodityId);

        logger.info("change commodity recommend status success.");
        return ResponseEntity.getSuccessEntity("修改推荐状态成功", executeNum);

    }


    /**
     * 删除商品
     *
     * @param request
     * @return
     */
    @RequestMapping("/removeCommodity")
    @ResponseBody
    public Object removeCommodity(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer commodityId = Integer.valueOf(jsonObject.get("commodityId").toString());
        logger.info("request remove commodity, commodity id is {}.", commodityId);

        int executeNum = commodityWebService.removeCommodity(commodityId);
        return ResponseEntity.getSuccessEntity("删除商品成功", executeNum);
    }


    /**
     * 修改商品
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateCommodity")
    @ResponseBody
    public Object updateCommodity(HttpServletRequest request) {
        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }
        CommodityVo commodityVo = JSON.parseObject(requestParam, CommodityVo.class);

        logger.info("request modify commodity, commodity id is {}", commodityVo.getpId());
        int executeNum = commodityWebService.updateCommodity(commodityVo);

        if (executeNum == 0) {
            logger.info("modify commodity fail");
            return ResponseEntity.getFailEntity("修改商品失败");
        }

        logger.info("modify commodity success");
        return ResponseEntity.getSuccessEntity("商品修改成功", executeNum);

    }


}
