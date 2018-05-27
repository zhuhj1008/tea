package com.joe.bussiness.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.CommodityItem;
import com.joe.bussiness.commodity.service.CommodityWebService;
import com.joe.bussiness.commodity.vo.CommodityVo;
import com.joe.util.mvc.RequestEntity;
import com.joe.util.mvc.ResultClientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Enumeration;

@Controller
@RequestMapping("/commodity")
public class CommodityController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);

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

        logger.info("新增商品，商品名称：{}",commodityVo.getpName());
        commodityWebService.addCommodity(commodityVo);
        logger.info("新增商品成功");

        return ResultClientEntity.getSuccessEntity();

    }

    @RequestMapping("/addCommodity1")
    @ResponseBody
    public Object addCommodity1(RequestEntity requestEntity){

        logger.info("param:{}",requestEntity);
        String body = requestEntity.getBody();
        CommodityVo commodityVo = JSON.parseObject(body, CommodityVo.class);
        commodityWebService.addCommodity(commodityVo);
        logger.info("新增商品成功");

        return ResultClientEntity.getSuccessEntity();

    }


    @RequestMapping("/addCommodity2")
    @ResponseBody
    public Object addCommodity2(HttpServletRequest requestEntity){

        Enumeration<String> parameterNames = requestEntity.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            String parameter = requestEntity.getParameter(paramName);
            logger.info("请求key:{},请求value：{}",paramName,parameter);
        }

        return ResultClientEntity.getSuccessEntity();

    }

    @RequestMapping("/addCommodity3")
    @ResponseBody
    public Object addCommodity3(HttpServletRequest request){

        try {
            ServletInputStream inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens = -1;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }
            String param = content.toString();// 内容
            logger.info("param:{}",param);
            CommodityVo commodityVo = JSON.parseObject(param, CommodityVo.class);
            commodityWebService.addCommodity(commodityVo);
            logger.info("新增商品成功");

        } catch (IOException e) {
            e.printStackTrace();
        }

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
