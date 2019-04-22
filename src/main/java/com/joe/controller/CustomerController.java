package com.joe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.UserCustomer;
import com.joe.common.base.BaseController;
import com.joe.service.CustomerWebService;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户信息请求
 * create by Joe on 2018-06-22 11:52
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{

    @Autowired
    private CustomerWebService customerWebService;


    @RequestMapping("/getCustomer")
    @ResponseBody
    public Object getCustomerOpenIdAndSessionKey(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数有误");
        }
        JSONObject jsonObject = JSON.parseObject(requestParam);
        String code = jsonObject.get("code").toString();

        if(StringUtils.isEmpty(code)){
            return ResponseEntity.getFailEntity("code为空");
        }

        UserCustomer userCustomer = customerWebService.getUserCustomer(code);

        if(userCustomer == null){
            return ResponseEntity.getFailEntity("获取用户信息失败");
        }

        return ResponseEntity.getSuccessEntity("请求成功",userCustomer);
    }
}
