package com.joe.manager.api.commodity;

import com.joe.api.po.CommodityEvaluate;
import com.joe.api.service.CommodityEvaluateService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestCommodityEvaluateService extends BaseTest {


    @Autowired
    CommodityEvaluateService commodityEvaluateService;

    @Test
    public void testAdd(){
        CommodityEvaluate commodityEvaluate = new CommodityEvaluate();
        commodityEvaluate.setCommodityId(4);
        commodityEvaluate.setCustomerId(2);
        commodityEvaluate.setCustomerName("dom");
        commodityEvaluate.setLevel(1);
        commodityEvaluate.setEvaluate("差评");

        commodityEvaluateService.addCommodityEvaluate(commodityEvaluate);
    }


    @Test
    public void testModify(){
        CommodityEvaluate commodityEvaluate = new CommodityEvaluate();
        commodityEvaluate.setEvaluateId(1);
        commodityEvaluate.setAppendEvaluate("真的很好");
        commodityEvaluate.setAppendEvaluateTime(new Date());
        commodityEvaluateService.updateCommodityEvaluate(commodityEvaluate);

    }
}
