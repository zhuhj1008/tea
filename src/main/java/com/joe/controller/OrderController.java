package com.joe.controller;

import com.joe.common.ApiParameter;
import com.joe.common.ApiResult;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.dto.order.*;
import com.joe.service.OrderWebService;
import com.joe.util.mvc.ResponsePageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单请求
 * create by Joe on 2018-06-04 16:02
 **/
@Slf4j
@RestController
@Api(tags = {"订单接口"})
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderWebService orderWebService;


    //新增订单
    @PostMapping("/addOrder")
    @ApiOperation(value = "新增订单", notes = "新增订单")
    public ApiResult addOrder(@RequestBody @ApiParam ApiParameter<OrderParam> apiParameter) {

        int orderId = orderWebService.addOrder(apiParameter.getBody());

        return ApiResult.getSuccessEntity(orderId);
    }


    /**
     * 查询订单
     */
    @PostMapping("/getOrder")
    @ApiOperation(value = "查询订单", notes = "条件查询订单")
    public ApiResult getOrder(@RequestBody @ApiParam ApiParameter<OrderQueryParam> apiParameter) {

        ResponsePageEntity pageEntity = orderWebService.getOrderList(apiParameter.getBody());

        return ApiResult.getSuccessEntity(pageEntity);
    }


    /**
     * 查询订单详情
     */
    @PostMapping("/getOrderDetail")
    @ApiOperation(value = "查询订单详情", notes = "查询订单详情")
    public ApiResult getOrderDetail(@RequestBody @ApiParam ApiParameter<OrderCommonParam> apiParameter) {

        List<OrderDetailVo> orderDetailList = orderWebService.getOrderDetailByOrderId(apiParameter.getBody().getOrderId());

        return ApiResult.getSuccessEntity(orderDetailList);
    }


    /**
     * 订单发货
     */
    @PostMapping("/deliver")
    @ApiOperation(value = "订单发货", notes = "修改订单状态为发货状态")
    public ApiResult orderDeliver(@RequestBody @ApiParam ApiParameter<OrderDeliverDTO> apiParameter) {

        orderWebService.orderDeliver(apiParameter.getBody());

        return ApiResult.getSuccessEntity();
    }


    /**
     * 微信支付
     */
    @PostMapping("/wePayUnifiedOrder")
    @ApiOperation(value = "订单微信支付", notes = "微信统一支付")
    public ApiResult wePayUnifiedOrder(@RequestBody ApiParameter<UnifiedParamDto> apiParameter) {

        UnifiedParamDto unifiedParamDto = apiParameter.getBody();
        log.info("微信支付-统一支付，请求参数：{}。", unifiedParamDto);
        unifiedParamDto.setSpbillCreateIp("127.0.0.1");

        Object o = orderWebService.wePayUnifiedOrder(unifiedParamDto);
        return ApiResult.getSuccessEntity(o);
    }

    /**
     * 微信回调接口
     */
    @PostMapping("/wxResend")
    @ApiOperation(value = "微信回调接口", notes = "微信回调通知")
    public void wxResend() {

    }

}
