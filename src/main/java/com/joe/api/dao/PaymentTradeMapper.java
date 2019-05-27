package com.joe.api.dao;

import com.joe.api.po.PaymentTrade;

public interface PaymentTradeMapper {

    int insertSelective(PaymentTrade record);
}