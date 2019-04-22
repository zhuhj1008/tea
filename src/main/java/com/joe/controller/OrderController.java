package com.joe.controller;

import com.joe.api.po.OrderDetail;
import com.joe.common.ApiParam;
import com.joe.common.ApiResult;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.dto.order.OrderDeliverDTO;
import com.joe.dto.order.OrderParam;
import com.joe.dto.order.OrderQueryDTO;
import com.joe.dto.order.OrderVo;
import com.joe.service.OrderDetailWebService;
import com.joe.service.OrderWebService;
import com.joe.util.mvc.ResponsePageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderWebService orderWebService;

    @Autowired
    private OrderDetailWebService orderDetailWebService;


    /**
     * 新增订单
     */
    @RequestMapping("/addOrder")
    public ApiResult addOrder(@RequestBody ApiParam<OrderVo> apiParam) {

        OrderVo orderVo = apiParam.getBody();
        log.info("新增订单，客户姓名：{}。", orderVo.getCustomerName());

        int orderId = orderWebService.addOrder(orderVo);
        log.info("新增订单完成。订单编号：{}。", orderId);

        orderDetailWebService.addOrderDetail(orderVo, orderId);
        log.info("新增订单明细完成，订单编号：{}。", orderId);

        return ApiResult.getSuccessEntity(orderId);
    }


    /**
     * 查询订单
     */
    @RequestMapping("/getOrder")
    public ApiResult getOrder(@RequestBody ApiParam<OrderQueryDTO> apiParam) {

        OrderQueryDTO orderQueryDTO = apiParam.getBody();
        log.info("查询订单，查询参数：{}。", orderQueryDTO);

        List<OrderVo> orderList = orderWebService.getOrderList(orderQueryDTO);
        int total = orderWebService.getOrderCount(orderQueryDTO);
        log.info("查询订单成功，订单数量：{}", total);

        ResponsePageEntity pageEntity = new ResponsePageEntity(total, orderList);
        return ApiResult.getSuccessEntity(pageEntity);
    }


    /**
     * 查询订单详情
     */
    @RequestMapping("/getOrderDetail")
    public ApiResult getOrderDetail(@RequestBody ApiParam<OrderParam> apiParam) {

        OrderParam param = apiParam.getBody();
        log.info("查询订单详情，订单编号：{}。", param.getOrderId());
        if (param.getOrderId() == null) {
            log.error("查询订单详情，订单编号为空。");
            return ApiResult.getFailEntity();
        }

        List<OrderDetail> orderDetailList = orderDetailWebService.getOrderDetailByOrderId(param.getOrderId());
        log.info("查询订单详情成功。");

        return ApiResult.getSuccessEntity(orderDetailList);
    }


    /**
     * 订单发货
     */
    @RequestMapping("/deliver")
    public ApiResult orderDeliver(@RequestBody ApiParam<OrderDeliverDTO> apiParam) {

        OrderDeliverDTO orderDeliverDTO = apiParam.getBody();
        log.info("请求订单发货，请求参数：{}。", orderDeliverDTO);

        int execNum = orderWebService.orderDeliver(orderDeliverDTO);
        if (execNum <= 0) {
            log.error("请求订单发货失败。");
            return ApiResult.getFailEntity();
        }
        return ApiResult.getSuccessEntity(execNum);
    }


    /**
     * 微信支付
     */
    @RequestMapping("/wePayUnifiedOrder")
    public ApiResult wePayUnifiedOrder(@RequestBody ApiParam<UnifiedParamDto> apiParam) {

        UnifiedParamDto unifiedParamDto = apiParam.getBody();
        log.info("微信支付-统一支付，请求参数：{}。", unifiedParamDto);
        unifiedParamDto.setSpbillCreateIp("127.0.0.1");

        Object o = orderWebService.wePayUnifiedOrder(unifiedParamDto);
        return ApiResult.getSuccessEntity(o);
    }

    /**
     * 微信回调接口
     */
    @RequestMapping("/wxResend")
    public void wxResend() {

    }

}
