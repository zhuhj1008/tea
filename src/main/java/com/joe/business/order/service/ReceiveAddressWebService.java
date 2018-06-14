package com.joe.business.order.service;

import com.joe.api.po.ReceiveAddress;
import com.joe.api.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址业务
 * create by Joe on 2018-06-14 16:03
 **/
@Service
public class ReceiveAddressWebService {

    @Autowired
    private ReceiveAddressService receiveAddressService;


    public int addReceiveAddress(ReceiveAddress receiveAddress){
        return receiveAddressService.addReceiveAddress(receiveAddress);
    }


    public int updateReceiveAddress(ReceiveAddress receiveAddress){

        return receiveAddressService.updateReceiveAddress(receiveAddress);
    }


    public int deleteReceiveAddress(int addressId){

        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setAddressId(addressId);
        receiveAddress.setEnable(false);
        return receiveAddressService.updateReceiveAddress(receiveAddress);
    }


    public List<ReceiveAddress> getReceiveAddressByCustomerId(Integer customerId){

        return receiveAddressService.queryReceiveAddressByCustomerId(customerId);
    }

}
