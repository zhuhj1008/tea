package com.joe.service;

import com.joe.Application;
import com.joe.dto.commodity.CommodityDetailVO;
import javafx.scene.control.Alert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommodityWebServiceTest {

    @Autowired
    CommodityWebService commodityWebService;

    @Test
    public void addCommodity() {
    }

    @Test
    public void queryCommodityByItemId() {
    }

    @Test
    public void queryCommodityCountByItemId() {
    }

    @Test
    public void removeCommodity() {
    }

    @Test
    public void updateCommodity() {
    }

    @Test
    public void queryRecommendCommodity() {
    }

    @Test
    public void updateRecommendStatus() {
    }

    @Test
    public void queryDetailByCommodityId() {
        CommodityDetailVO commodityDetailVO = commodityWebService.queryDetailByCommodityId(111);
        Assert.assertNotNull(commodityDetailVO);

    }
}