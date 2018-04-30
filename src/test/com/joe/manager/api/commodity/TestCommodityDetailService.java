package com.joe.manager.api.commodity;

import com.joe.api.po.CommodityDetail;
import com.joe.api.service.CommodityDetailService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TestCommodityDetailService extends BaseTest {

    @Autowired
    CommodityDetailService commodityDetailService;

    @Test
    public void testAdd(){
        CommodityDetail commodityDetail = new CommodityDetail();
        commodityDetail.setCommodityId(6);
        commodityDetail.setUnit("50g");
        commodityDetail.setProperty("");
        commodityDetail.setFreight(new BigDecimal(9.00));
        commodityDetail.setIntegral(16);
        commodityDetail.setOrigin("北京");
        commodityDetailService.addCommodityDetail(commodityDetail);
    }

    @Test
    public void testModify(){
        CommodityDetail commodityDetail = new CommodityDetail();
        commodityDetail.setCommodityId(1);
        commodityDetail.setEnable(true);
        commodityDetailService.modifyCommodityDetail(commodityDetail);
    }
}
