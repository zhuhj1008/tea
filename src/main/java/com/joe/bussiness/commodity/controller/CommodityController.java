package com.joe.bussiness.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityItem;
import com.joe.bussiness.base.BaseController;
import com.joe.bussiness.commodity.service.CommodityWebService;
import com.joe.bussiness.commodity.vo.CommodityDetailVO;
import com.joe.bussiness.commodity.vo.CommodityVo;
import com.joe.bussiness.exception.ParameterIllegalityException;
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

        logger.info("新增商品，商品名称:{}", commodityVo.getpName());
        int commodityId = commodityWebService.addCommodity(commodityVo);
        logger.info("新增商品成功,商品Id：{}", commodityId);

        return ResponseEntity.getSuccessEntity(commodityId);
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
            return ResponseEntity.getFailEntity("param error");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer itemId = Integer.valueOf(jsonObject.get("itemId").toString());
        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());

        int total = commodityWebService.queryCommodityCountByItemId(itemId);
        List<Commodity> commodityList = commodityWebService.queryCommodityByItemId(itemId, pageNo, pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("contents", commodityList);

        return ResponseEntity.getSuccessEntity(data);
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
            return ResponseEntity.getFailEntity("param error");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer commodityId = Integer.valueOf(jsonObject.get("commodityId").toString());

        commodityWebService.removeCommodity(commodityId);
        return ResponseEntity.getSuccessEntity("删除成功");
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
            return ResponseEntity.getFailEntity("param error");
        }
        CommodityVo commodityVo = JSON.parseObject(requestParam, CommodityVo.class);

        logger.info("修改商品，商品编号：{}", commodityVo.getpId());
        commodityWebService.updateCommodity(commodityVo);
        logger.info("修改商品成功");

        return ResponseEntity.getSuccessEntity("修改成功");
    }


}
