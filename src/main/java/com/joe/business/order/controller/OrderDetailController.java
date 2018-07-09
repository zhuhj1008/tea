package com.joe.business.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.OrderDetail;
import com.joe.business.common.base.BaseController;
import com.joe.business.common.exception.ParameterIllegalityException;
import com.joe.business.order.service.OrderDetailWebService;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单详情请求
 * create by Joe on 2018-06-04 16:04
 **/
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController extends BaseController {

    @Autowired
    private OrderDetailWebService orderDetailWebService;


    @RequestMapping("/getOrderDetail")
    public Object getOrderDetail(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            throw new ParameterIllegalityException("参数格式不正确");
        }
        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        Integer orderId = Integer.valueOf(jsonObject.get("orderId").toString());

        List<OrderDetail> orderDetailList = orderDetailWebService.getOrderDetailByOrderId(orderId);

        return ResponseEntity.getSuccessEntity("获取订单详情成功", orderDetailList);
    }

}
