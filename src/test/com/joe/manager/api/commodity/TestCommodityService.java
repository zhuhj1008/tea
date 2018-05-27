package com.joe.manager.api.commodity;

import com.joe.api.po.Commodity;
import com.joe.api.service.CommodityService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestCommodityService extends BaseTest {

    @Autowired
    CommodityService commodityService;

    @Test
    public void testAdd(){
        List<String>  nameList = new ArrayList<>();
//        nameList.add("祁门红茶"); nameList.add("大吉岭红茶"); nameList.add("阿萨姆红茶");
        nameList.add("吴裕泰");
//        nameList.add("天福茗茶");
//        nameList.add("张一元");

        Commodity commodity = new Commodity();
        commodity.setItemId(5);
        commodity.setPicture("www.tea.green.com");
        commodity.setCreateBy(1);
        commodity.setUpdateBy(1);
        for (int i = 0; i < nameList.size(); i++) {
            commodity.setCommodityId(null);
            commodity.setCommodityName(nameList.get(i));
            commodity.setPrice(new BigDecimal(30.5).multiply(new BigDecimal(i)));
            commodityService.addCommodity(commodity);
        }

    }


    @Test
    public void testUpdate(){
        Commodity commodity = new Commodity();
        commodity.setCommodityId(1);
        commodity.setPrice(new BigDecimal(100.2));
        int i = commodityService.modifyCommodity(commodity);
        logger.info("修改条数：{}",i);



    }

    @Test
    public void testSelect(){
        Commodity commodity = commodityService.queryCommodityById(1);
        logger.info("id为1的商品是：{}",commodity);

        List<Commodity> commodities = commodityService.queryCommodityByItemId(4);
        logger.info("所有的绿茶商品：{}",commodities);
    }

    @Test
    public void testDrop(){
        int i = commodityService.dropCommodity(1);
        logger.info("删除记录数：{}",i);
    }
}
