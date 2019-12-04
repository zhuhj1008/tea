package com.joe.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joe.api.dao.CommodityDetailMapper;
import com.joe.api.dao.CommodityMapper;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import com.joe.dto.commodity.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommodityService {

    @Autowired
    private CommodityDetailMapper commodityDetailMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityPictureService commodityPictureService;


    // 新增商品
    public int addCommodity(Commodity commodity) {

        commodity.setCreateTime(new Date());
        commodity.setUpdateTime(new Date());
        commodity.setEnable(true);
        commodityMapper.insertSelective(commodity);

        return commodity.getCommodityId();

    }

    //删除商品（逻辑删除）
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
        int j = commodityDetailMapper.updateByCommodityIdSelective(commodityDetail);

        Integer k = commodityPictureService.deleteByCommodityId(commodityId);

        return i + j + k;
    }


    //修改商品信息 （全部更新）
    public int modifyCommodityById(Commodity commodity) {

        return commodityMapper.updateByPrimaryKey(commodity);
    }

    //修改商品推荐状态（0改成1 1改成0）
    public int updateRecommendStatusById(int commodityId) {

        return commodityMapper.updateRecommendStatusById(commodityId);
    }


    //查询商品
    public Commodity queryCommodityById(int commodityId) {

        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    //分页查询商品集合By 类目Id
    public PageInfo<Commodity> queryCommodityByItemId(int itemId, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<Commodity> commodities = commodityMapper.selectByItemId(itemId);
        return new PageInfo<>(commodities);

    }


    /**
     * 查询推荐商品
     *
     * @return
     */
    public List<CommodityVo> queryRecommendCommodity() {

        return commodityMapper.selectRecommendCommodity();
    }


}
