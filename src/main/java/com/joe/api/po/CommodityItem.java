package com.joe.api.po;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 商品类目bean
 */
@Data
public class CommodityItem {


    //类目编号
    private Integer itemId;

    //类目名称
    private String name;

    //上级类目
    private Integer parentId;

    //上级类目链
    private String parentIds;

    //创建者
    private Integer createBy;

    //最后更新人
    private Integer updateBy;

    //创建时间
    private Date createTime;

    //最后一次更新时间
    private Date updateTime;

    //可用
    private Boolean enable;

}