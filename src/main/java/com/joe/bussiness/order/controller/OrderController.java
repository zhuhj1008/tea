package com.joe.bussiness.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.dto.OrderQueryDTO;
import com.joe.bussiness.base.BaseController;
import com.joe.bussiness.order.service.OrderDetailWebService;
import com.joe.bussiness.order.service.OrderWebService;
import com.joe.bussiness.order.vo.OrderVo;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.collections.bidimap.AbstractBidiMapDecorator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 订单请求
 * create by Joe on 2018-06-04 16:02
 **/
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {


    @Autowired
    private OrderWebService orderWebService;

    @Autowired
    private OrderDetailWebService orderDetailWebService;


    //新增订单
    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        OrderVo orderVo = JSON.parseObject(requestParam, OrderVo.class);

        int orderId = orderWebService.addOrder(orderVo);
        orderDetailWebService.addOrderDetail(orderVo,orderId);

        return ResponseEntity.getSuccessEntity(null, null);
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public Object getOrderByParam(HttpServletRequest request){
        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if(jsonObject == null){
            return ResponseEntity.getFailEntity("参数错误");
        }

        Integer pageNo = Integer.valueOf(jsonObject.get("pageNo").toString());
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());

        OrderQueryDTO dto = new OrderQueryDTO();
        if(jsonObject.get("customer_name") != null){
            dto.setExpressCode(jsonObject.get("customer_name").toString());
        }
        if(jsonObject.get("express_code") != null){
            dto.setCustomerName(jsonObject.get("express_code").toString());
        }
        if(jsonObject.get("order_status") != null){
            dto.setOrderStatus(Integer.valueOf(jsonObject.get("order_status").toString()));
        }

        List<OrderVo> orderList = orderWebService.getOrderList(dto, pageNo, pageSize);
        int orderCount = orderWebService.getOrderCount(dto);

        Map<String,Object> data = new HashedMap();
        data.put("total", orderCount);
        data.put("contents", orderList);
        return ResponseEntity.getSuccessEntity("查询成功",data);
    }




}
