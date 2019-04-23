package com.joe.controller;

import com.joe.api.po.ReceiveAddress;
import com.joe.common.ApiParam;
import com.joe.common.ApiResult;
import com.joe.dto.order.AddressParam;
import com.joe.service.ReceiveAddressWebService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址请求
 * create by Joe on 2018-06-14 14:41
 **/
@Slf4j
@Api(tags={"收货地址"})
@RestController
@RequestMapping("/receive")
public class ReceiveAddressController {

    @Autowired
    private ReceiveAddressWebService receiveAddressWebService;

    /**
     * 添加收货地址
     */
    @RequestMapping("/addReceiveAddress")
    public ApiResult addReceiveAddress(@RequestBody ApiParam<ReceiveAddress> apiParam) {

        ReceiveAddress receiveAddress = apiParam.getBody();
        log.info("添加收货地址，请求参数：{}。", receiveAddress);

        Integer addressCount = receiveAddressWebService.getReceiveAddressCountByCustomerId(receiveAddress.getCustomerId());
        log.info("添加收货地址，用户现有收货地址数量：{}。", addressCount);
        if (addressCount != null && addressCount >= 10) {
            log.info("添加收货地址，用户收货地址超出限制。");
            return ApiResult.getFailEntity("收货地址最多为10个");
        }

        int addressId = receiveAddressWebService.addReceiveAddress(receiveAddress);
        log.info("新增收货地址成功，编号：{}。", addressId);

        return ApiResult.getSuccessEntity(addressId);
    }

    /**
     * 修改收货地址
     */
    @RequestMapping("/updateReceiveAddress")
    public ApiResult updateReceiveAddress(@RequestBody ApiParam<ReceiveAddress> apiParam) {


        ReceiveAddress receiveAddress = apiParam.getBody();
        log.info("修改收货地址，编号：{}。", receiveAddress.getAddressId());

        int addressId = receiveAddressWebService.updateReceiveAddress(receiveAddress);
        if (addressId == 0) {
            log.error("修改收货地址信息失败");
            return ApiResult.getFailEntity();
        }

        return ApiResult.getSuccessEntity(addressId);
    }

    /**
     * 删除收货地址
     */
    @RequestMapping("/deleteReceiveAddress")
    public Object deleteReceiveAddress(@RequestBody ApiParam<AddressParam> apiParam) {

        AddressParam param = apiParam.getBody();
        log.info("删除收货地址，请求参数：{}。", param);

        if (param.getAddressId() == null) {
            log.error("删除收货地址，请求编号为空。");
        }

        int execNum = receiveAddressWebService.deleteReceiveAddress(param.getAddressId());
        if (execNum <= 0) {
            return ApiResult.getFailEntity();
        }

        return ApiResult.getSuccessEntity(execNum);
    }

    /**
     * 查询收货地址
     */
    @RequestMapping("/queryReceiveAddress")
    public Object queryReceiveAddress(@RequestBody ApiParam<AddressParam> apiParam) {

        AddressParam param = apiParam.getBody();
        log.info("查询收货地址，请求参数：{}。", param);
        if (param.getCustomerId() == null) {
            log.error("查询收货地址，客户编号为空。");
        }

        List<ReceiveAddress> addressList = receiveAddressWebService.getReceiveAddressByCustomerId(param.getCustomerId());
        log.info("查询收货地址成功。");

        return ApiResult.getSuccessEntity(addressList);
    }


    /**
     * 查询默认收货地址
     *
     * @return
     */
    @RequestMapping("/queryDefaultAddress")
    public Object queryCustomerDefaultReceiveAddress(@RequestBody ApiParam<AddressParam> apiParam) {

        AddressParam param = apiParam.getBody();
        log.info("查询默认收货地址，请求参数：{}。", param);
        if (param.getCustomerId() == null) {
            log.error("查询默认收货地址，客户编号为空。");
        }

        ReceiveAddress address = receiveAddressWebService.getCustomerDefaultReceiveAddress(param.getCustomerId());
        log.info("查询默认收货地址成功，默认地址：{}。", address);

        return ApiResult.getSuccessEntity(address);
    }

    /**
     * 修改用户默认收货地址
     */
    @RequestMapping("/modifyDefaultAddress")
    public Object modifyCustomerDefaultReceiveAddress(@RequestBody ApiParam<AddressParam> apiParam) {

        AddressParam param = apiParam.getBody();
        log.info("修改默认收货地址，请求参数：{}。", param);

        if (param.getCustomerId() == null || param.getAddressId() == null) {
            log.error("修改默认收货地址失败。");
        }

        int executeNum = receiveAddressWebService.modifyDefaultReceiveAddress(param.getCustomerId(), param.getAddressId());
        if (executeNum == 0) {
            log.error("修改默认收货地址失败。");
            return ApiResult.getFailEntity();
        }

        return ApiResult.getSuccessEntity(executeNum);
    }

}
