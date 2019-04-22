package com.joe.api.po;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 客户Bean
 */
@Data
public class UserCustomer {

    //客户编号
    private Integer customerId;

    //微信唯一标识
    private String openId;

    //小程序唯一标识
    private String unionId;

    //头像
    private String headPortrait;

    //客户姓名
    private String customerName;

    //手机号
    private String mobile;

    //邮箱
    private String email;

    //用户类型（UserTypeEnum）
    private Integer userType;

    //总积分
    private Integer integral;

    //默认收货地址
    private Integer receivingAddressDefault;

    //创建人
    private Integer createBy;

    //创建时间
    private Date createTime;

    //更新人
    private Integer updateBy;

    //更新时间
    private Date updateTime;

    //可用
    private Boolean enable;

}