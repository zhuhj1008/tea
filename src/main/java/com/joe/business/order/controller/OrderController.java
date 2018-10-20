package com.joe.business.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.business.order.service.OrderDetailWebService;
import com.joe.business.order.service.OrderWebService;
import com.joe.business.order.vo.OrderDeliverDTO;
import com.joe.business.order.vo.OrderQueryDTO;
import com.joe.business.order.vo.OrderVo;
import com.joe.common.base.BaseController;
import com.joe.common.exception.ParameterIllegalityException;
import com.joe.common.wx.dto.UnifiedParamDto;
import com.joe.util.mvc.ResponseEntity;
import com.joe.util.mvc.ResponsePageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单请求
 * create by Joe on 2018-06-04 16:02
 **/
@RestController
@RequestMapping("/order")
@Slf4j
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

    @RequestMapping("/testLog")
    public void testLog() {
        log.info("joe....");
    }


    /**
     * 条件查询订单
     */
    @RequestMapping("/getOrder")
    public Object getOrderByParam(HttpServletRequest request) {
        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            throw new ParameterIllegalityException("请求参数有误");
        }

        OrderQueryDTO orderQueryDTO = JSON.parseObject(requestParam, OrderQueryDTO.class);

        List<OrderVo> orderList = orderWebService.getOrderList(orderQueryDTO);
        int total = orderWebService.getOrderCount(orderQueryDTO);

        ResponsePageEntity pageEntity = new ResponsePageEntity();
        pageEntity.setTotal(total);
        pageEntity.setContents(orderList);
        return ResponseEntity.getSuccessEntity("查询订单列表成功", pageEntity);
    }

    @RequestMapping("/test")
    public void testParam(@RequestBody Student student){

        System.out.println(student);

    }


    /**
     * 修改订单为发货状态
     */
    @RequestMapping("/deliver")
    public Object alterOrder(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            throw new ParameterIllegalityException("请求参数有误");
        }

        OrderDeliverDTO orderDeliverDTO = JSON.parseObject(requestParam, OrderDeliverDTO.class);

        int i = orderWebService.orderDeliver(orderDeliverDTO);
        if (i <= 0) {
            return ResponseEntity.getFailEntity("订单发货失败");
        }
        return ResponseEntity.getSuccessEntity("订单发货成功", i);
    }


    /**
     * 订单微信支付
     * @return
     */
    @RequestMapping("/wePayUnifiedOrder")
    public Object wePayUnifiedOrder(HttpServletRequest request){

        String ipAddress = request.getRemoteAddr();
        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            throw new ParameterIllegalityException("参数为空");
        }
        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        UnifiedParamDto unifiedParamDto = JSON.parseObject(requestParam, UnifiedParamDto.class);
        unifiedParamDto.setSpbillCreateIp(ipAddress);

        return orderWebService.wePayUnifiedOrder(unifiedParamDto);
    }


    @RequestMapping("/wxResend")
    public void wxResend(HttpServletRequest request){
        System.out.println("微信回调成功");
        String requestParam = getRequestParam(request);
        System.out.println(requestParam);
    }

}
