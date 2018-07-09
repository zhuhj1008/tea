package com.joe.api.service;

import com.joe.api.dao.OrderDetailMapper;
import com.joe.api.enums.OrderDetailEnum;
import com.joe.api.po.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    /**
     * 新增订单明细
     *
     * @param orderDetail
     * @return
     */
    public int addOrderDetail(OrderDetail orderDetail) {

        orderDetailMapper.insertSelective(orderDetail);
        return orderDetail.getDetailId();
    }

    /**
     * 批量新增订单详情
     * @param orderDetailList
     */
    public void addOrderDetailBatch(List<OrderDetail> orderDetailList){
        orderDetailMapper.insertBatch(orderDetailList);
    }


    /**
     * 修改订单明细By detailId
     *
     * @param orderDetail
     * @return
     */
    public int modifyOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
    }

    /**
     * 修改订单详情状态
     *
     * @param orderId         订单ID
     * @param orderDetailEnum 订单详情状态
     * @return 修改记录数
     */
    public int modifyOrderDetailStatus(int orderId, OrderDetailEnum orderDetailEnum) {

        if (orderDetailEnum == null) {
            return 0;
        }

        return orderDetailMapper.updateStatusByOrderId(orderId, orderDetailEnum.getValue());
    }


    /**
     * 查询订单明细by DetailId
     *
     * @param detailId
     * @return
     */
    public OrderDetail queryOrderDetail(int detailId) {
        return orderDetailMapper.selectByPrimaryKey(detailId);
    }

    /**
     * 查询购物车
     *
     * @param customerId
     * @return
     */
    public List<OrderDetail> queryShopCartDetail(int customerId) {
        return orderDetailMapper.selectByCustomerIdAndStatus(customerId,OrderDetailEnum.SHOP_CAR.getValue());
    }

    /**
     * 根据订单号查询订单列表
     * @param orderId
     * @return
     */
    public List<OrderDetail> queryOrderDetailByOrderId(Integer orderId){

        if(orderId == null || orderId == 0){
            return new ArrayList<>();
        }

        return orderDetailMapper.queryOrderDetailByOrderId(orderId);
    }


}
