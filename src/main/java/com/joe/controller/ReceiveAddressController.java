package com.joe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.api.po.ReceiveAddress;
import com.joe.common.base.BaseController;
import com.joe.service.ReceiveAddressWebService;
import com.joe.util.mvc.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收货地址请求
 * create by Joe on 2018-06-14 14:41
 **/
@Slf4j
@RestController
@RequestMapping("/receive")
public class ReceiveAddressController extends BaseController {

    @Autowired
    private ReceiveAddressWebService receiveAddressWebService;

    @RequestMapping("/addReceiveAddress")
    public Object addReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        ReceiveAddress receiveAddress = JSON.parseObject(requestParam, ReceiveAddress.class);

        Integer addressCount = receiveAddressWebService.getReceiveAddressCountByCustomerId(receiveAddress.getCustomerId());

        if (addressCount != null && addressCount > 10) {
            return ResponseEntity.getFailEntity("用户地址数量超过了10个");
        }

        int addressId = receiveAddressWebService.addReceiveAddress(receiveAddress);

        return ResponseEntity.getSuccessEntity("新增收货地址成功", addressId);
    }

    @RequestMapping("/updateReceiveAddress")
    public Object updateReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        ReceiveAddress receiveAddress = JSON.parseObject(requestParam, ReceiveAddress.class);

        int addressId = receiveAddressWebService.updateReceiveAddress(receiveAddress);

        if (addressId == 0) {
            return ResponseEntity.getFailEntity("修改收货地址信息失败");
        }

        return ResponseEntity.getSuccessEntity("修改收货地址信息成功", addressId);
    }

    @RequestMapping("/deleteReceiveAddress")
    public Object deleteReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer addressId = Integer.valueOf(jsonObject.get("addressId").toString());

        int i = receiveAddressWebService.deleteReceiveAddress(addressId);
        if (i == 0) {
            return ResponseEntity.getFailEntity("删除收货地址信息失败");
        }
        return ResponseEntity.getSuccessEntity("删除收货地址信息成功", i);

    }

    @RequestMapping("/queryReceiveAddress")
    public Object queryReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer customerId = Integer.valueOf(jsonObject.get("customerId").toString());

        List<ReceiveAddress> receiveAddressByCustomerId = receiveAddressWebService.getReceiveAddressByCustomerId(customerId);

        return ResponseEntity.getSuccessEntity("查询收货地址信息成功", receiveAddressByCustomerId);
    }


    /**
     * 查询用户默认收货地址
     *
     * @return
     */
    @RequestMapping("/queryDefaultAddress")
    public Object queryCustomerDefaultReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer customerId = Integer.valueOf(jsonObject.get("customerId").toString());

        ReceiveAddress receiveAddress = receiveAddressWebService.getCustomerDefaultReceiveAddress(customerId);

        if (receiveAddress == null) {
            return ResponseEntity.getFailEntity("没有默认收货地址");
        }

        return ResponseEntity.getSuccessEntity("查询默认收货地址成功", receiveAddress);
    }

    /**
     * 修改用户默认收货地址
     * @param request
     * @return
     */
    @RequestMapping("/modifyDefaultAddress")
    public Object modifyCustomerDefaultReceiveAddress(HttpServletRequest request) {

        String requestParam = getRequestParam(request);
        if (StringUtils.isBlank(requestParam)) {
            return ResponseEntity.getFailEntity("参数错误");
        }

        JSONObject jsonObject = JSON.parseObject(requestParam);
        if (jsonObject == null) {
            return ResponseEntity.getFailEntity("参数错误");
        }
        Integer customerId = Integer.valueOf(jsonObject.get("customerId").toString());
        Integer addressId = Integer.valueOf(jsonObject.get("addressId").toString());


        int executeNum = receiveAddressWebService.modifyCustomerDefaultReceiveAddress(customerId, addressId);

        if (executeNum == 0) {
            return ResponseEntity.getFailEntity("修改默认收货地址失败");
        }

        return ResponseEntity.getSuccessEntity("修改默认收货地址成功", executeNum);
    }

}
