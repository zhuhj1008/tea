package com.joe.bussiness.commodity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commodity")
public class CommodityController {


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
     * 查询商品详情
     * @param commodityId
     * @return
     */
    @RequestMapping("getCommodityDetail")
    public Object getCommodityDetail(int commodityId){
        return null;
    }


}
