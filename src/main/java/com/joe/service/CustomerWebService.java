package com.joe.service;

import com.joe.api.po.UserCustomer;
import com.joe.api.service.UserCustomerService;
import com.joe.common.wx.service.WxService;
import com.joe.dto.wx.WxLoginDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户服务
 * create by Joe on 2018-06-22 11:51
 **/
@Service
public class CustomerWebService {


    @Autowired
    private WxService wxService;

    @Autowired
    private UserCustomerService userCustomerService;


    //根据用户请求code获取用户信息
    public UserCustomer getUserCustomer(String code) {

        WxLoginDto wxLoginDto = wxService.wxLoginAuthorization(code);

        if (wxLoginDto == null || StringUtils.isEmpty(wxLoginDto.getOpenId())) {
            return null;
        }

        UserCustomer userCustomer = userCustomerService.queryCustomerByOpenId(wxLoginDto.getOpenId());
        if (userCustomer == null) {
            userCustomer = new UserCustomer();
            userCustomer.setOpenId(wxLoginDto.getOpenId());
            userCustomer.setUnionId(wxLoginDto.getUnionId());
            int customerId = userCustomerService.addCustomer(userCustomer);
            userCustomer.setCustomerId(customerId);
        }

        return userCustomer;
    }

}
