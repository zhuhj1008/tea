package com.joe.api.service;

import com.joe.api.dao.PaymentTradeMapper;
import com.joe.api.po.PaymentTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTradeService {

    @Autowired
    private PaymentTradeMapper paymentTradeMapper;

    public int save(PaymentTrade paymentTrade) {

        return paymentTradeMapper.insertSelective(paymentTrade);
    }
}
