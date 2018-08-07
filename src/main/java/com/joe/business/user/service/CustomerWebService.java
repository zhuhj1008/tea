package com.joe.business.user.service;

import com.alibaba.fastjson.JSON;
import com.joe.api.po.UserCustomer;
import com.joe.api.service.UserCustomerService;
import com.joe.common.wx.dto.AuthParamDto;
import com.joe.common.wx.service.WxService;
import com.joe.business.user.dto.WxLoginDto;
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
    private UserCustomerService userCustomerService;

    @Autowired
    private WxService wxService;


    //根据用户请求code获取用户信息
    public UserCustomer getUserCustomer(String code) {

        AuthParamDto param = new AuthParamDto();


        String response = wxService.getOpenIdAndSessionKey(code);

        WxLoginDto wxLoginDto = JSON.parseObject(response, WxLoginDto.class);

        if (wxLoginDto == null) {
            return null;
        }
        String openId = wxLoginDto.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            return null;
        }
        UserCustomer userCustomer = userCustomerService.queryCustomerByOpenId(openId);

        if (userCustomer == null) {
            userCustomer = new UserCustomer();
            userCustomer.setOpenId(wxLoginDto.getOpenId());
            userCustomer.setUnionId(wxLoginDto.getUnionId() == null ? wxLoginDto.getExpiresIn() : wxLoginDto.getUnionId());
            int customerId = userCustomerService.addCustomer(userCustomer);
            userCustomer.setCustomerId(customerId);
        }

        return userCustomer;
    }

}
