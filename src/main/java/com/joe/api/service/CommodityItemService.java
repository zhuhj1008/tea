package com.joe.api.service;


import com.joe.api.dao.CommodityItemMapper;
import com.joe.api.po.CommodityItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommodityItemService {

    @Autowired
    CommodityItemMapper commodityItemMapper;

    /**
     * 新增商品类目
     *
     * @param commodityItem 商品类目
     * @return 类目id（主键）
     */
    public int addCommodityItem(CommodityItem commodityItem) {

        commodityItem.setCreateTime(new Date());
        commodityItem.setUpdateTime(new Date());
        commodityItem.setEnable(true);
        commodityItemMapper.insertSelective(commodityItem);

        return commodityItem.getItemId();
    }

    /**
     * 修改商品类目
     *
     * @param commodityItem 商品类目
     */
    public void modifyCommodityItem(CommodityItem commodityItem) {
        commodityItemMapper.updateByPrimaryKeySelective(commodityItem);
    }

    /**
     * 查询商品类目
     *
     * @param itemId 类目ID
     */
    public CommodityItem queryCommodityItemById(int itemId) {

        return commodityItemMapper.selectByItemId(itemId);

    }

    /**
     * 根据商品类目名称查询（唯一，精准）
     *
     * @param name
     * @return
     */
    public CommodityItem queryCommodityItemByName(String name) {

        return commodityItemMapper.selectByItemName(name);

    }

    /**
     * 查询自己和下级商品ID类目集合
     * @param itemId
     * @return
     */
    public List<Integer> selectSubItemIdList(Integer itemId) {

        return commodityItemMapper.selectSubItemIdList(itemId);

    }


}
