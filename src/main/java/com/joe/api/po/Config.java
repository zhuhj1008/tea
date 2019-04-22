package com.joe.api.po;

import lombok.Data;

import java.util.Date;

/**
 * 配置bean
 */
@Data
public class Config {

    /*主键*/
    private Integer id;

    /*配置key*/
    private String configKey;

    /*配置value*/
    private String configValue;

    /*配置类型*/
    private Integer configType;

    /*备注*/
    private String remake;

    /*创建时间*/
    private Date createTime;

    /*可用*/
    private Boolean enable;

}