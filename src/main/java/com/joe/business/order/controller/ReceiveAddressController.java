package com.joe.business.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.ReceiveAddress;
import com.joe.api.service.ReceiveAddressService;
import com.joe.business.common.base.BaseController;
import com.joe.business.order.service.ReceiveAddressWebService;
import com.joe.util.mvc.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收货地址请求
 * create by Joe on 2018-06-14 14:41
 **/
@Controller
@RequestMapping("/receive")
public class ReceiveAddressController extends BaseController{

    @Autowired
    private ReceiveAddressWebService receiveAddressWebService;

    @RequestMapping("/addReceiveAddress")
    @ResponseBody
    public Object addReceiveAddress(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        ReceiveAddress receiveAddress = JSON.parseObject(requestParam, ReceiveAddress.class);

        int addressId = receiveAddressWebService.addReceiveAddress(receiveAddress);

        return ResponseEntity.getSuccessEntity("新增收货地址成功",addressId);
    }

    @RequestMapping("/updateReceiveAddress")
    @ResponseBody
    public Object updateReceiveAddress(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        ReceiveAddress receiveAddress = JSON.parseObject(requestParam, ReceiveAddress.class);

        int addressId = receiveAddressWebService.updateReceiveAddress(receiveAddress);

        if(addressId == 0){
            return ResponseEntity.getFailEntity("修改收货地址信息失败");
        }

        return ResponseEntity.getSuccessEntity("修改收货地址信息成功",addressId);
    }

    @RequestMapping("/deleteReceiveAddress")
    @ResponseBody
    public Object deleteReceiveAddress(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if(jsonObject == null){
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer addressId = Integer.valueOf(jsonObject.get("addressId").toString());

        int i = receiveAddressWebService.deleteReceiveAddress(addressId);
        if(i == 0){
            return ResponseEntity.getFailEntity("删除收货地址信息失败");
        }
        return ResponseEntity.getSuccessEntity("删除收货地址信息成功",i);

    }

    @RequestMapping("/queryReceiveAddress")
    @ResponseBody
    public Object queryReceiveAddress(HttpServletRequest request){

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if(jsonObject == null){
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer customerId = Integer.valueOf(jsonObject.get("customerId").toString());

        List<ReceiveAddress> receiveAddressByCustomerId = receiveAddressWebService.getReceiveAddressByCustomerId(customerId);

        return ResponseEntity.getSuccessEntity("查询收货地址信息成功",receiveAddressByCustomerId);
    }


}
