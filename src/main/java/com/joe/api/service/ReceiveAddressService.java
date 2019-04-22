package com.joe.api.service;

import com.joe.api.dao.ReceiveAddressMapper;
import com.joe.api.po.ReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收货地址业务
 * create by Joe on 2018-06-14 15:22
 **/
@Service
public class ReceiveAddressService {

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    /**
     * 新增收货地址
     *
     * @param receiveAddress
     * @return
     */
    public int addReceiveAddress(ReceiveAddress receiveAddress) {

        receiveAddress.setCreateTime(new Date());
        receiveAddress.setEnable(true);
        receiveAddressMapper.insertSelective(receiveAddress);
        return receiveAddress.getAddressId();
    }

    /**
     * 修改收货地址
     *
     * @param receiveAddress
     * @return
     */
    public int updateReceiveAddress(ReceiveAddress receiveAddress) {

        if (receiveAddress == null || receiveAddress.getAddressId() == null) {
            return 0;
        }
        receiveAddress.setUpdateTime(new Date());
        return receiveAddressMapper.updateByPrimaryKeySelective(receiveAddress);
    }

    /**
     * 根据主键查询收货地址
     *
     * @param addressId
     * @return
     */
    public ReceiveAddress queryReceiveAddressById(Integer addressId) {

        if (addressId == null || addressId == 0) {
            return null;
        }
        return receiveAddressMapper.selectByPrimaryKey(addressId);
    }

    /**
     * 查询用户收货地址数量
     *
     * @param customerId
     * @return
     */
    public Integer queryUserReceiveAddressCount(Integer customerId) {

        if (customerId == null || customerId == 0) {
            return null;
        }
        return receiveAddressMapper.selectCountByCustomerId(customerId);
    }


    /**
     * 根据客户编号查询收货地址列表
     *
     * @param customerId
     * @return
     */
    public List<ReceiveAddress> queryReceiveAddressByCustomerId(Integer customerId) {

        if (customerId == null || customerId == 0) {
            return new ArrayList<>();
        }

        Integer count = queryUserReceiveAddressCount(customerId);

        if (count == null || count == 0) {
            return new ArrayList<>();
        }

        return receiveAddressMapper.selectByCustomerId(customerId);
    }


}
