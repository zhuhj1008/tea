package com.joe.bussiness.commodity.controller;

import com.joe.api.po.CommodityItem;
import com.joe.bussiness.commodity.service.CommodityWebService;
import com.joe.bussiness.commodity.vo.CommodityVo;
import com.joe.util.mvc.ResultClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityWebService commodityWebService;


    /**
     * 根据商品类目编号查找类目名称
     * @param itemId
     * @return
     */
    @RequestMapping("/getItemById")
    @ResponseBody
    public Object getCommodityItemById(int itemId){

        CommodityItem item = commodityWebService.getItemById(itemId);

        return ResultClientEntity.getSuccessEntity(item);
    }


    /**
     * 查询商品分类类表
     * @return
     */
    @RequestMapping("/getAllItem")
    @ResponseBody
    public Object getAllCommodityItem() {

        return null;
    }

    /**
     * 添加商品
     * @param commodityVo
     * @return
     */
    @RequestMapping("/addCommodity")
    @ResponseBody
    public Object addCommodity(CommodityVo commodityVo){

        commodityWebService.addCommodity(commodityVo);
        return ResultClientEntity.getSuccessEntity();

    }




    /**
     * 查询商品详情
     * @param commodityId
     * @return
     */
    @RequestMapping("/getCommodityDetail")
    @ResponseBody
    public Object getCommodityDetail(int commodityId){
        return "abc";
    }





}
