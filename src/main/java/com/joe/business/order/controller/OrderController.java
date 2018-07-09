package com.joe.business.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.dto.OrderQueryDTO;
import com.joe.business.common.base.BaseController;
import com.joe.business.common.exception.ParameterIllegalityException;
import com.joe.business.order.service.OrderDetailWebService;
import com.joe.business.order.service.OrderWebService;
import com.joe.business.order.vo.OrderVo;
import com.joe.util.mvc.ResponseEntity;
import com.joe.util.mvc.ResponsePageEntity;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

/**
 * 订单请求
 * create by Joe on 2018-06-04 16:02
 **/
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {


    @Autowired
    private OrderWebService orderWebService;

    @Autowired
    private OrderDetailWebService orderDetailWebService;


    /**
     * 新增订单
     */
    @RequestMapping("/addOrder")
    public Object addOrder(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        OrderVo orderVo = JSON.parseObject(requestParam, OrderVo.class);

        int orderId = orderWebService.addOrder(orderVo);
        orderDetailWebService.addOrderDetail(orderVo, orderId);

        return ResponseEntity.getSuccessEntity(null, null);
    }

    /**
     * 条件查询订单
     */
    @RequestMapping("/getOrder")
    public Object getOrderByParam(HttpServletRequest request) {
        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());

        OrderQueryDTO dto = new OrderQueryDTO();
        if (jsonObject.get("customerName") != null) {
            dto.setExpressCode(jsonObject.get("customerName").toString());
        }
        if (jsonObject.get("expressCode") != null) {
            dto.setCustomerName(jsonObject.get("expressCode").toString());
        }
        if (jsonObject.get("orderStatus") != null) {
            dto.setOrderStatus(Integer.valueOf(jsonObject.get("orderStatus").toString()));
        }

        List<OrderVo> orderList = orderWebService.getOrderList(dto, pageNo, pageSize);
        int total = orderWebService.getOrderCount(dto);

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(orderList);
        return ResponseEntity.getSuccessEntity("查询订单列表成功", pageEntity);
    }


    @RequestMapping
    public Object alterOrder(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
           throw new ParameterIllegalityException("参数格式不正确");
        }
        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        Integer orderId = Integer.valueOf(jsonObject.get("orderId").toString());




        return ResponseEntity.getSuccessEntity("修改订单成功", 1);
    }


}
