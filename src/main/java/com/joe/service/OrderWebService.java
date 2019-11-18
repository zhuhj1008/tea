package com.joe.service;


import com.github.pagehelper.PageInfo;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.api.service.OrderService;
import com.joe.common.exception.BusinessException;
import com.joe.dto.ApiPageResult;
import com.joe.dto.order.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单业务
 * create by Joe on 2018-06-04 16:03
 **/
@Slf4j
@Service
public class OrderWebService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WxService wxService;

    @Autowired
    private OrderDetailService orderDetailService;


    //新增订单
    public int addOrder(OrderParam orderParam) {

        log.info("新增订单，客户姓名：{}。", orderParam.getCustomerName());

        String orderNo = orderService.getOrderNo(orderParam.getCustomerId());
        Order order = new Order();
        order.setOrderNo(orderNo);
        BeanUtils.copyProperties(orderParam, order);
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setEnable(true);
        int orderId = orderService.addOrder(order);
        log.info("新增订单完成。订单编号：{}，订单号：{}。", orderId, orderNo);

        List<OrderDetailParam> orderDetailVoList = orderParam.getOrderDetailArr();
        List<OrderDetail> orderDetailList = orderDetailVoList.stream().map(orderDetailParam -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(orderDetailParam, orderDetail);
            orderDetail.setOrderId(orderId);
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetailService.addOrderDetailBatch(orderDetailList);
        log.info("新增订单明细完成，订单编号：{}。", orderId);

        return orderId;
    }


    //分页条件查询订单列表
    public ApiPageResult getOrderList(OrderQueryParam orderQueryParam) {

        log.info("查询订单，查询参数：{}。", orderQueryParam);
        Order queryOrder = new Order();
        if (StringUtils.isNotEmpty(orderQueryParam.getCustomerName())) {
            queryOrder.setCustomerName(orderQueryParam.getCustomerName());
        }
        if (StringUtils.isNotEmpty(orderQueryParam.getExpressCode())) {
            queryOrder.setExpressCode(orderQueryParam.getExpressCode());
        }
        if (orderQueryParam.getExpressCode() != null) {
            queryOrder.setOrderStatus(orderQueryParam.getOrderStatus());
        }

        PageInfo<Order> orderPageInfo = orderService.queryOrderListByQueryDto(queryOrder, orderQueryParam.getPageNo(), orderQueryParam.getPageSize());

        List<OrderVo> orderVos = order2OrderVoList(orderPageInfo.getList());
        log.info("查询订单成功。");

        return new ApiPageResult(orderPageInfo.getTotal(), orderVos);
    }


    //订单发货
    public int orderDeliver(OrderDeliverDTO orderDeliverDTO) {

        log.info("请求订单发货，请求参数：{}。", orderDeliverDTO);

        Integer orderId = orderDeliverDTO.getOrderId();
        if (orderId == null || orderId == 0) {
            throw new BusinessException("此订单不存在");
        }

        Order order = orderService.queryOrder(orderId);
        if (order == null) {
            throw new BusinessException("此订单不存在");
        }

        //如果订单状态不是已支付，不允许修改成发货状态
        if (order.getOrderStatus() == null || order.getOrderStatus() != OrderStatusEnum.PAY_SUCCESS.getCode()) {
            throw new BusinessException("此订单未支付");
        }

        order.setExpressId(orderDeliverDTO.getExpressId());
        order.setExpressCode(orderDeliverDTO.getExpressCode());
        order.setExpressMoney(orderDeliverDTO.getExpressMoney());
        order.setOrderStatus(OrderStatusEnum.DELIVER.getCode());
        order.setDeliveryTime(new Date());

        return orderService.modifyOrder(order);
    }

    //根据订单号查询订单明细
    public List<OrderDetailVo> getOrderDetailByOrderId(Integer orderId) {

        log.info("查询订单详细信息，订单编号：{}", orderId);
        if (orderId == null || orderId == 0) {
            return new ArrayList<>();
        }

        List<OrderDetail> orderDetails = orderDetailService.queryOrderDetailByOrderId(orderId);

        List<OrderDetailVo> result = orderDetails.stream().map(orderDetail -> {
            OrderDetailVo orderDetailVo = new OrderDetailVo();
            BeanUtils.copyProperties(orderDetail, orderDetailVo);
            return orderDetailVo;
        }).collect(Collectors.toList());
        log.info("查询订单详情成功。");

        return result;
    }

    //根据客户编号查询订单列表
    public ApiPageResult getOrderListByCustomerId(CustomerOrderParam param) {

        Integer orderId = param.getCustomerId();
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        PageInfo<Order> orderPage = orderService.queryByCustomerId(orderId, pageNo, pageSize);

        List<OrderVo> orderVos = order2OrderVoList(orderPage.getList());
        log.info("查询订单成功。");
        return new ApiPageResult(orderPage.getTotal(), orderVos);
    }

    private List<OrderVo> order2OrderVoList(List<Order> orderList) {

        if (CollectionUtils.isEmpty(orderList)) {
            return new ArrayList<>();
        }
        return orderList.stream().map(order -> {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            orderVo.setOrderStatus(OrderStatusEnum.getTextByCode(order.getOrderStatus()));
            return orderVo;
        }).collect(Collectors.toList());
    }

}
