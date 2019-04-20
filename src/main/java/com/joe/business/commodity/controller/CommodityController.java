package com.joe.business.commodity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.business.commodity.vo.CommodityParam;
import com.joe.common.ApiParam;
import com.joe.common.base.BaseController;
import com.joe.business.commodity.service.CommodityWebService;
import com.joe.business.commodity.vo.CommodityVo;
import com.joe.util.mvc.ResponseEntity;
import com.joe.util.mvc.ResponsePageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品管理
 */
@Slf4j
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityWebService commodityWebService;


    @RequestMapping("/addCommodity")
    public Object addCommodity(@RequestBody ApiParam<CommodityVo> apiParam) {

        CommodityVo commodityVo = apiParam.getBody();

        log.info("request add commodity, commodity name is {}.", commodityVo.getpName());
        int commodityId = commodityWebService.addCommodity(commodityVo);
        log.info("add commodity success, commodity id is {}.", commodityId);

        return ResponseEntity.getSuccessEntity("新增商品成功", commodityId);
    }


    /**
     * 根据类目id查询商品集合
     */
    @RequestMapping("/getCommodityListByItemId")
    public Object getCommodityListByItemId(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam param = apiParam.getBody();
        Integer itemId = param.getItemId();
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        log.info("request commodity list, param  item {}, pageNo {}, pagesSize{}.", itemId, pageNo, pageSize);

        int total = commodityWebService.queryCommodityCountByItemId(itemId);
        List<Commodity> commodityList = commodityWebService.queryCommodityByItemId(itemId, pageNo, pageSize);
        log.info("request commodity list success.");

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
    public Object getRecommendCommodity() {

        log.info("request recommend commodity list");
        List<Commodity> commodities = commodityWebService.queryRecommendCommodity();
        log.info("request recommend commodity list success, total {}", commodities.size());

        return ResponseEntity.getSuccessEntity("查询推荐商品成功", commodities);
    }


    /**
     * 切换商品推荐/不推荐
     */
    @RequestMapping("/switchCommodityRecommend")
    public Object switchCommodityRecommend(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam param = apiParam.getBody();

        Integer commodityId = param.getCommodityId();
        log.info("request change commodity recommend status, commodity id is {}.", commodityId);

        int executeNum = commodityWebService.updateRecommendStatus(commodityId);

        log.info("change commodity recommend status success.");
        return ResponseEntity.getSuccessEntity("修改推荐状态成功", executeNum);

    }


    /**
     * 删除商品
     */
    @RequestMapping("/removeCommodity")
    public Object removeCommodity(@RequestBody ApiParam<CommodityParam> apiParam) {

        CommodityParam param = apiParam.getBody();
        Integer commodityId = param.getCommodityId();
        log.info("request remove commodity, commodity id is {}.", commodityId);

        int executeNum = commodityWebService.removeCommodity(commodityId);
        return ResponseEntity.getSuccessEntity("删除商品成功", executeNum);
    }


    /**
     * 修改商品
     */
    @RequestMapping("/updateCommodity")
    public Object updateCommodity(@RequestBody ApiParam<CommodityVo> apiParam) {

        CommodityVo commodityVo = apiParam.getBody();

        log.info("request modify commodity, commodity id is {}", commodityVo.getpId());
        int executeNum = commodityWebService.updateCommodity(commodityVo);

        if (executeNum == 0) {
            log.info("modify commodity fail");
            return ResponseEntity.getFailEntity("修改商品失败");
        }

        log.info("modify commodity success");
        return ResponseEntity.getSuccessEntity("商品修改成功", executeNum);
    }


}
