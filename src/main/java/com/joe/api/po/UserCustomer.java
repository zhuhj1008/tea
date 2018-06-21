package com.joe.api.po;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 客户Bean
 */
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getReceivingAddressDefault() {
        return receivingAddressDefault;
    }

    public void setReceivingAddressDefault(Integer receivingAddressDefault) {
        this.receivingAddressDefault = receivingAddressDefault;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}