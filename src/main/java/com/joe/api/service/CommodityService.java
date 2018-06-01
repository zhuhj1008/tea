package com.joe.api.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joe.api.dao.CommodityDetailMapper;
import com.joe.api.dao.CommodityItemMapper;
import com.joe.api.dao.CommodityMapper;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommodityService {

    @Autowired
    CommodityDetailMapper commodityDetailMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityItemMapper commodityItemMapper;


    /**
     * 新增商品
     *
     * @param commodity
     * @return commodityId （主键）
     */
    public int addCommodity(Commodity commodity) {

        commodity.setCreateTime(new Date());
        commodity.setUpdateTime(new Date());
        commodity.setEnable(true);
        commodityMapper.insertSelective(commodity);

        return commodity.getCommodityId();

    }

    /**
     * 删除商品（逻辑删除）
     *
     * @param commodityId 商品编号
     * @return 删除个数
     */
    @Transactional
    public int dropCommodity(int commodityId) {

        Commodity commodity = new Commodity();
        commodity.setCommodityId(commodityId);
        commodity.setUpdateTime(new Date());
        commodity.setEnable(false);
        int i = commodityMapper.updateByPrimaryKeySelective(commodity);
        CommodityDetail commodityDetail = new CommodityDetail();
        commodityDetail.setCommodityId(commodityId);
        commodityDetail.setEnable(false);
        int i1 = commodityDetailMapper.updateByCommodityIdSelective(commodityDetail);

        return i + i1;
    }


    /**
     * 修改商品信息（选择更新）
     *
     * @param commodity
     * @return 修改的个数
     */
    public int modifyCommoditySelective(Commodity commodity) {

        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }

    /**
     * 修改商品信息 （全部更新）
     * @param commodity
     * @return
     */
    public int modifyCommodityById(Commodity commodity){

        return commodityMapper.updateByPrimaryKey(commodity);
    }





    /**
     * 查询单个商品By商品Id
     *
     * @param commodityId
     * @return
     */
    public Commodity queryCommodityById(int commodityId) {

        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    /**
     * 查询商品集合By 类目Id
     *
     * @param itemId
     * @param pageNo 页数
     * @param pageSize 每页记录数
     * @return
     */
    public List<Commodity> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);

        return commodityMapper.selectByItemId(itemId);

    }

    /**
     * 查询商品个数
     * @param itemId
     * @return
     */
    public int queryCommodityCountByItemId(int itemId){

        return commodityMapper.selectCommodityCountByItemId(itemId);
    }



}
