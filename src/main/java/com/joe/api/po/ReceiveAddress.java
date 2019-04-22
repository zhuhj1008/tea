package com.joe.api.po;

import lombok.Data;

import java.util.Date;

@Data
public class ReceiveAddress {

    //主键
    private Integer addressId;

    //顾客编号
    private Integer customerId;

    //收货姓名
    private String name;

    //手机号
    private String mobilePhone;

    //省份
    private String province;

    //城市
    private String city;

    //地区
    private String area;

    //街道
    private String street;

    //邮编
    private String postCode;

    //详细地址
    private String addressDetail;

    //创建日期
    private Date createTime;

    //更新日期
    private Date updateTime;

    //可用
    private Boolean enable;

}