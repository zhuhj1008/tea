package com.joe.bussiness.order.controller;

import com.alibaba.fastjson.JSON;
import com.joe.bussiness.base.BaseController;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单请求
 * create by Joe on 2018-06-04 16:02
 **/
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {


    //新增订单

    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

//        JSON.parseObject(requestParam, )


        return ResponseEntity.getSuccessEntity(null, null);
    }


}
