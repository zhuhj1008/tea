package com.joe.dto.wx;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnifiedParam {


    private String openId;

    private String ip;

    private String orderNo;

    private BigDecimal totalFee;

    private String body;

}
