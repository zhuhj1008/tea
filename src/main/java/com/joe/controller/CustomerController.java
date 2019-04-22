package com.joe.controller;

import com.joe.api.po.UserCustomer;
import com.joe.common.ApiParam;
import com.joe.common.ApiResult;
import com.joe.dto.user.UserParam;
import com.joe.service.CustomerWebService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户信息请求
 * create by Joe on 2018-06-22 11:52
 **/
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerWebService customerWebService;

    @RequestMapping("/getCustomer")
    public ApiResult getCustomerOpenIdAndSessionKey(@RequestBody ApiParam<UserParam> apiParam) {

        UserParam param = apiParam.getBody();
        log.info("获取客户信息，请求code：{}。", param.getCode());
        if (StringUtils.isEmpty(param.getCode())) {
            log.error("获取客户信息失败，请求code为空。");
            return ApiResult.getFailEntity();
        }

        UserCustomer userCustomer = customerWebService.getUserCustomer(param.getCode());
        if (userCustomer == null) {
            return ApiResult.getFailEntity();
        }

        log.info("获取客户信息成功。");
        return ApiResult.getSuccessEntity(userCustomer);
    }
}
