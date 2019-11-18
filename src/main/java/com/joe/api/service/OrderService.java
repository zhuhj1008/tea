package com.joe.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joe.api.dao.OrderMapper;
import com.joe.api.enums.OrderStatusEnum;
import com.joe.api.po.Order;
import com.joe.common.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    /* 订单号（时间戳+客户号+随机数） */
    public String getOrderNo(Integer customerId) {

        //时间戳，精确到秒 10位
        long time = System.currentTimeMillis() / 1000;

        //客户号，4位，不足前边补0
        String customerIdStr = String.format("%04d", customerId);

        //随机数，4位，不足前边补0
        int random = (int) (Math.random() * 10000);
        String randomStr = String.format("%04d", random);

        return time + customerIdStr + randomStr;
    }

    /**
     * 创建新订单
     *
     * @param order
     * @return
     */
    public int addOrder(Order order) {

        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMapper.insertSelective(order);
        return order.getOrderId();
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 订单号
     */
    public int modifyOrder(Order order) {

        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除订单（逻辑删除）By 订单号
     *
     * @param orderId 订单号
     * @return 删除记录数
     */
    public int deleteOrder(int orderId) {

        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 查询订单By 订单号
     *
     * @param orderId 订单号
     * @return 订单
     */
    public Order queryOrder(int orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }


    /**
     * 根据条件查询订单个数
     *
     * @param order
     * @return
     */
    public int queryOrderCount(Order order) {

        return orderMapper.selectCountByOrderSelective(order);
    }

    /**
     * 根据条件查询订单
     *
     * @param order
     * @return
     */
    public PageInfo<Order> queryOrderListByQueryDto(Order order, Integer pageNo, Integer pageSize) {

        if (pageNo == null || pageNo == 0) {
            pageNo = GlobalConstant.PageConstant.DEFAULT_PAGE_NO;
        }

        if (pageSize == null || pageSize == 0) {
            pageNo = GlobalConstant.PageConstant.DEFAULT_PAGE_SIZE;
        }

        PageHelper.startPage(pageNo, pageSize);

        List<Order> orderList = orderMapper.selectByOrderSelective(order);

        return new PageInfo<>(orderList);

    }


    /**
     * 根据客户编号查询订单
     *
     * @param customerId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Order> queryByCustomerId(Integer customerId, Integer pageNo, Integer pageSize) {

        if (customerId == null) {
            return new PageInfo<>(new ArrayList<>());
        }
        PageHelper.startPage(pageNo, pageSize);

        List<Order> orders = orderMapper.selectByCustomerId(customerId);
        return new PageInfo<>(orders);
    }


}
