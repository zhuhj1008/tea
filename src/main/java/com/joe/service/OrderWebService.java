package com.joe.service;


import com.alibaba.fastjson.JSON;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import com.joe.api.po.OrderDetail;
import com.joe.api.service.OrderDetailService;
import com.joe.api.service.OrderService;
import com.joe.common.exception.BusinessException;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.common.wx.dto.UnifiedSuccessDto;
import com.joe.common.wx.enums.WxTradeTypeEnum;
import com.joe.common.wx.service.WxService;
import com.joe.dto.order.OrderDeliverDTO;
import com.joe.dto.order.OrderDetailVo;
import com.joe.dto.order.OrderQueryParam;
import com.joe.dto.order.OrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private WxService wxService;

    @Autowired
    private OrderDetailService orderDetailService;


    /**
     * 新增订单
     */
    public int addOrder(OrderVo orderVo) {

        Order order = new Order();
        BeanUtils.copyProperties(orderVo, order);
        int orderId = orderService.addOrder(order);

        return orderId;
    }

    /**
     * 查询符合条件的订单数量
     */
    public int getOrderCount(OrderQueryParam dto) {

        Order order = JSON.parseObject(JSON.toJSONString(dto), Order.class);
        return orderService.queryOrderCount(order);
    }


    /**
     * 分页条件查询订单列表
     */
    public List<OrderVo> getOrderList(OrderQueryParam orderQueryParam) {

        Order order = new Order();
        if (StringUtils.isNotEmpty(orderQueryParam.getCustomerName())) {
            order.setCustomerName(orderQueryParam.getCustomerName());
        }
        if (StringUtils.isNotEmpty(orderQueryParam.getExpressCode())) {
            order.setExpressCode(orderQueryParam.getExpressCode());
        }
        if (orderQueryParam.getExpressCode() != null) {
            order.setOrderStatus(orderQueryParam.getOrderStatus());
        }

        List<Order> orders = orderService.queryOrderListByQueryDto(order, orderQueryParam.getPageNo(), orderQueryParam.getPageSize());
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order tmpOrder : orders) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(tmpOrder, orderVo);
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


    /**
     * 订单申请微信支付
     */
    public Object wePayUnifiedOrder(UnifiedParamDto unifiedParamDto) {

        unifiedParamDto.setBody(unifiedParamDto.getBody());
        unifiedParamDto.setOutTradeNo(unifiedParamDto.getOutTradeNo());
        unifiedParamDto.setSpbillCreateIp(unifiedParamDto.getSpbillCreateIp());
        unifiedParamDto.setTotalFee(unifiedParamDto.getTotalFee());
        unifiedParamDto.setDeviceInfo("WEB");
        unifiedParamDto.setSignType("MD5");
        unifiedParamDto.setTradeType(WxTradeTypeEnum.JSAPI.getType());
        unifiedParamDto.setOpenId(unifiedParamDto.getOpenId());

        UnifiedSuccessDto unifiedSuccessDto = wxService.wePayUnifiedOrder(unifiedParamDto);

        return unifiedSuccessDto;
    }


    /**
     * 新增订单明细
     */
    public void addOrderDetail(OrderVo orderVo, int orderId) {

        List<OrderDetailVo> orderDetailVoList = orderVo.getOrderDetailArr();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailVo orderDetailVo : orderDetailVoList) {
            OrderDetail orderDetail = OrderDetailVo.convert2OrderDetail(orderDetailVo, orderId);
            orderDetailList.add(orderDetail);
        }

        orderDetailService.addOrderDetailBatch(orderDetailList);
    }

    /**
     * 根据订单号查询订单明细
     */
    public List<OrderDetail> getOrderDetailByOrderId(Integer orderId) {

        if (orderId == null || orderId == 0) {
            return new ArrayList<>();
        }

        return orderDetailService.queryOrderDetailByOrderId(orderId);
    }

}