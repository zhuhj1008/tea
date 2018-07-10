package com.joe.business.order.service;


import com.alibaba.fastjson.JSON;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import com.joe.api.service.OrderService;
import com.joe.business.common.exception.BusinessException;
import com.joe.business.order.vo.OrderDeliverDTO;
import com.joe.business.order.vo.OrderQueryDTO;
import com.joe.business.order.vo.OrderVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单业务
 * create by Joe on 2018-06-04 16:03
 **/
@Service
public class OrderWebService {

    @Autowired
    private OrderService orderService;


    /**
     * 新增订单
     */
    public int addOrder(OrderVo orderVo) {

        Order order = OrderVo.OrderVoConvert2Order(orderVo);
        int orderId = orderService.addOrder(order);

        return orderId;
    }

    /**
     * 查询符合条件的订单数量
     */
    public int getOrderCount(OrderQueryDTO dto) {

        Order order = JSON.parseObject(JSON.toJSONString(dto), Order.class);
        return orderService.queryOrderCount(order);
    }


    /**
     * 分页条件查询订单列表
     */
    public List<OrderVo> getOrderList(OrderQueryDTO orderQueryDTO) {

        Order order = new Order();
        if (StringUtils.isNotEmpty(orderQueryDTO.getCustomerName())) {
            order.setCustomerName(orderQueryDTO.getCustomerName());
        }
        if (StringUtils.isNotEmpty(orderQueryDTO.getExpressCode())) {
            order.setExpressCode(orderQueryDTO.getExpressCode());
        }
        if (orderQueryDTO.getExpressCode() != null) {
            order.setOrderStatus(orderQueryDTO.getOrderStatus());
        }

        List<Order> orders = orderService.queryOrderListByQueryDto(order, orderQueryDTO.getPageNo(), orderQueryDTO.getPageSize());
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order tmpOrder : orders) {
            OrderVo orderVo = OrderVo.OrderConvert2OrderVo(tmpOrder);
            orderVoList.add(orderVo);
        }

        return orderVoList;
    }


    /**
     * @param orderDeliverDTO 订单发货参数
     * @return
     */
    public int orderDeliver(OrderDeliverDTO orderDeliverDTO) {

        Integer orderId = orderDeliverDTO.getOrderId();

        if (orderId == null || orderId == 0) {
            throw new BusinessException("此订单不存在");
        }

        Order order = orderService.queryOrder(orderId);
        if (order == null) {
            throw new BusinessException("此订单不存在");
        }

        //如果订单状态不是已支付，不允许修改成发货状态
        if (order.getOrderStatus() == null || order.getOrderStatus() != OrderStatusEnum.PAYMENT.getValue()) {
            throw new BusinessException("此订单未支付");
        }

        order.setExpressId(orderDeliverDTO.getExpressId());
        order.setExpressCode(orderDeliverDTO.getExpressCode());
        order.setExpressMoney(orderDeliverDTO.getExpressMoney());
        order.setOrderStatus(OrderStatusEnum.DELIVER.getValue());
        order.setDeliveryTime(new Date());

        return orderService.modifyOrder(order);
    }


}
