package com.joe.api.po;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentTrade {

    private Integer id;

    private String outTradeNo;

    private String openId;

    private String returnCode;

    private String returnMsg;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    private String transactionId;

    private Integer totalFee;

    private String bankType;

    private String timeEnd;

    private Date createTime;

    private Boolean enable;

}