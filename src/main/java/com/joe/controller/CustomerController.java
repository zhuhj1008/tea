package com.joe.controller;

import com.joe.api.po.UserCustomer;
import com.joe.common.ApiParameter;
import com.joe.common.ApiResult;
import com.joe.dto.user.UserParam;
import com.joe.service.CustomerWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户信息请求
 * create by Joe on 2018-06-22 11:52
 **/
@Slf4j
@Api(tags={"客户接口"})
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerWebService customerWebService;

    @PostMapping("/getCustomer")
    @ApiOperation(value = "查询客户信息", notes = "根据授权code查询用户信息")
    public ApiResult getCustomerOpenIdAndSessionKey(@RequestBody ApiParameter<UserParam> apiParameter) {

        UserParam param = apiParameter.getBody();
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
