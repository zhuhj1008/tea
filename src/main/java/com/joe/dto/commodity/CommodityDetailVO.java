package com.joe.dto.commodity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.Commodity;
import com.joe.api.po.CommodityDetail;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品详情VO
 * create by Joe on 2018-05-29 10:57
 **/
@Data
public class CommodityDetailVO {

    //商品名称
    private String name;

    //商品类目
    private Integer itemId;

    //小图地址
    private String smallPicture;

    //售价
    private BigDecimal price;

    //明细编号
    private Integer detailId;

    //商品编号
    private Integer commodityId;

    //成本
    private BigDecimal cost;

    //原价
    private BigDecimal initPrice;

    //描述
    private String description;

    //是否首页展示
    private Boolean recommend;

    //库存
    private Integer stock;

    //细节图
    private List<String> detailPicture;

    //图信息
    private List<String> pictureInfo;

    //产地
    private String origin;

    //口味
    private String flavor;

    //保存方法
    private String saveMethod;

    //保质期
    private String saveDate;

    public static CommodityDetailVO commodity2DetailVo(CommodityDetailVO vo, Commodity commodity) {

        if (commodity == null) {
            return vo;
        }

        vo.setName(commodity.getCommodityName());
        vo.setSmallPicture(commodity.getPicture());
        vo.setItemId(commodity.getItemId());
        vo.setPrice(commodity.getPrice());
        vo.setDescription(commodity.getDescription());
        vo.setRecommend(commodity.getRecommend());
        return vo;

    }

    public static CommodityDetailVO commodityDetail2DetailVo(CommodityDetailVO vo, CommodityDetail commodityDetail) {

        if (commodityDetail == null) {
            return vo;
        }

        vo.setDetailId(commodityDetail.getDetailId());
        vo.setCommodityId(commodityDetail.getCommodityId());
        vo.setCost(commodityDetail.getCost());
        vo.setInitPrice(commodityDetail.getInitPrice());
        vo.setStock(commodityDetail.getStock());

        String detailPicture = commodityDetail.getDetailPicture();
        List<String> detailPictureList = JSON.parseArray(detailPicture, String.class);
        vo.setDetailPicture(detailPictureList);

        String pictureInfo = commodityDetail.getPictureInfo();
        List<String> pictureInfoList = JSON.parseArray(pictureInfo, String.class);
        vo.setDetailPicture(detailPictureList);

        vo.setPictureInfo(pictureInfoList);
        vo.setOrigin(commodityDetail.getOrigin());

        if (StringUtils.isNotEmpty(commodityDetail.getProperty())) {
            JSONObject property = (JSONObject) JSON.parse(commodityDetail.getProperty());
            vo.setFlavor(property.getString("pTaste"));
            vo.setSaveMethod(property.getString("pPreservation"));
            vo.setSaveDate(property.getString("pExpirationDate"));
        }

        return vo;

    }

}
