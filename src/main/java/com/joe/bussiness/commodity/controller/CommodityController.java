package com.joe.bussiness.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityItem;
import com.joe.bussiness.base.BaseController;
import com.joe.bussiness.commodity.service.CommodityWebService;
import com.joe.bussiness.commodity.vo.CommodityDetailVO;
import com.joe.bussiness.commodity.vo.CommodityVo;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private CommodityWebService commodityWebService;


    /**
     * 查询商品分类类表
     * @return
     */
    @RequestMapping("/getAllItem")
    @ResponseBody
    public Object getAllCommodityItem() {

        return null;
    }

    @RequestMapping("/addCommodity")
    @ResponseBody
    public Object addCommodity(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if(StringUtils.isBlank(requestParam)){
            return ResponseEntity.getFailEntity("参数错误");
        }

        CommodityVo commodityVo = JSON.parseObject(requestParam, CommodityVo.class);

        logger.info("新增商品，商品名称:{}",commodityVo.getpName());
        int commodityId = commodityWebService.addCommodity(commodityVo);
        logger.info("新增商品成功,商品Id：{}",commodityId);

        return ResponseEntity.getSuccessEntity(commodityId);

    }


    /**
     * 根据类目id查询商品集合
     * @param request
     * @return
     */
    @RequestMapping("/getCommodityListByItemId")
    @ResponseBody
    public Object getCommodityListByItemId(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if(StringUtils.isBlank(requestParam)){
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        Integer itemId =(Integer) jsonObject.get("itemId");

        List<Commodity> commodityList = commodityWebService.queryCommodityByItemId(itemId);

        return ResponseEntity.getSuccessEntity(commodityList);

    }



    /**
     * 根据商品类目编号查找类目名称
     * @param itemId
     * @return
     */
    @RequestMapping("/getItemById")
    @ResponseBody
    public Object getCommodityItemById(int itemId){

        CommodityItem item = commodityWebService.getItemById(itemId);

        return ResponseEntity.getSuccessEntity(item);
    }




}
