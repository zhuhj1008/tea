package com.joe.service;

import com.joe.api.po.ReceiveAddress;
import com.joe.api.po.UserCustomer;
import com.joe.api.service.ReceiveAddressService;
import com.joe.api.service.UserCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址业务
 * create by Joe on 2018-06-14 16:03
 **/
@Service
public class ReceiveAddressWebService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveAddressWebService.class);

    @Autowired
    private ReceiveAddressService receiveAddressService;

    @Autowired
    private UserCustomerService userCustomerService;


    public int addReceiveAddress(ReceiveAddress receiveAddress) {
        return receiveAddressService.addReceiveAddress(receiveAddress);
    }


    public int updateReceiveAddress(ReceiveAddress receiveAddress) {

        return receiveAddressService.updateReceiveAddress(receiveAddress);
    }


    public int deleteReceiveAddress(int addressId) {

        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setAddressId(addressId);
        receiveAddress.setEnable(false);
        return receiveAddressService.updateReceiveAddress(receiveAddress);
    }


    public List<ReceiveAddress> getReceiveAddressByCustomerId(Integer customerId) {

        return receiveAddressService.queryReceiveAddressByCustomerId(customerId);
    }


    public ReceiveAddress getCustomerDefaultReceiveAddress(Integer userId) {

        UserCustomer userCustomer = userCustomerService.queryCustomerByCustomerId(userId);
        if (userCustomer == null) {
            logger.info("query customer by id, no result was found, customerId is {}", userId);
            return null;
        }
        return receiveAddressService.queryReceiveAddressById(userCustomer.getReceivingAddressDefault());
    }

    public int modifyDefaultReceiveAddress(Integer customerId, Integer addressId) {

        if (customerId == null || customerId == 0) {
            logger.info("modify default customer receive address,  invalid customerId :{}", customerId);
            return 0;
        }

        if (addressId == null || addressId == 0) {
            logger.info("modify default customer receive address,  invalid addressId :{}", addressId);
            return 0;
        }

        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setCustomerId(customerId);
        userCustomer.setReceivingAddressDefault(addressId);
        return userCustomerService.modifyCustomer(userCustomer);

    }


    /**
     * 查询用户收货地址个数
     *
     * @param customerId
     * @return
     */
    public Integer getReceiveAddressCountByCustomerId(int customerId) {

        return receiveAddressService.queryUserReceiveAddressCount(customerId);
    }

}
