package com.joe.controller;

import com.joe.api.po.ReceiveAddress;
import com.joe.common.ApiParameter;
import com.joe.common.ApiResult;
import com.joe.dto.order.AddressParam;
import com.joe.service.ReceiveAddressWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址请求
 * create by Joe on 2018-06-14 14:41
 **/
@Slf4j
@Api(tags={"收货地址接口"})
@RestController
@RequestMapping("/receive")
public class ReceiveAddressController {

    @Autowired
    private ReceiveAddressWebService receiveAddressWebService;

    /**
     * 添加收货地址
     */
    @PostMapping("/addReceiveAddress")
    @ApiOperation(value = "添加收货地址", notes = "添加收货地址，最多不能超过10个")
    public ApiResult addReceiveAddress(@RequestBody ApiParameter<ReceiveAddress> apiParameter) {

        ReceiveAddress receiveAddress = apiParameter.getBody();
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
    @PostMapping("/updateReceiveAddress")
    @ApiOperation(value = "修改收货地址", notes = "修改收货地址")
    public ApiResult updateReceiveAddress(@RequestBody ApiParameter<ReceiveAddress> apiParameter) {


        ReceiveAddress receiveAddress = apiParameter.getBody();
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
    @PostMapping("/deleteReceiveAddress")
    @ApiOperation(value = "删除收货地址", notes = "逻辑删除收货地址")
    public Object deleteReceiveAddress(@RequestBody ApiParameter<AddressParam> apiParameter) {

        AddressParam param = apiParameter.getBody();
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
    @PostMapping("/queryReceiveAddress")
    @ApiOperation(value = "查询收货地址列表", notes = "根据客户编号查询收货地址")
    public Object queryReceiveAddress(@RequestBody ApiParameter<AddressParam> apiParameter) {

        AddressParam param = apiParameter.getBody();
        log.info("查询收货地址，请求参数：{}。", param);
        if (param.getCustomerId() == null) {
            log.error("查询收货地址，客户编号为空。");
            return ApiResult.getFailEntity();
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
    @PostMapping("/queryDefaultAddress")
    @ApiOperation(value = "查询默认收货地址", notes = "根据客户编号查询默认收货地址")
    public Object queryCustomerDefaultReceiveAddress(@RequestBody ApiParameter<AddressParam> apiParameter) {

        AddressParam param = apiParameter.getBody();
        log.info("查询默认收货地址，请求参数：{}。", param);
        if (param.getCustomerId() == null) {
            log.error("查询默认收货地址，客户编号为空。");
            return ApiResult.getFailEntity();
        }

        ReceiveAddress address = receiveAddressWebService.getCustomerDefaultReceiveAddress(param.getCustomerId());
        log.info("查询默认收货地址成功，默认地址：{}。", address);

        return ApiResult.getSuccessEntity(address);
    }

    /**
     * 修改用户默认收货地址
     */
    @PostMapping("/modifyDefaultAddress")
    @ApiOperation(value = "指定默认收货地址", notes = "指定默认收货地址")
    public Object modifyCustomerDefaultReceiveAddress(@RequestBody ApiParameter<AddressParam> apiParameter) {

        AddressParam param = apiParameter.getBody();
        log.info("修改默认收货地址，请求参数：{}。", param);

        if (param.getCustomerId() == null || param.getAddressId() == null) {
            log.error("修改默认收货地址失败。");
            return ApiResult.getFailEntity();
        }

        int executeNum = receiveAddressWebService.modifyDefaultReceiveAddress(param.getCustomerId(), param.getAddressId());
        if (executeNum == 0) {
            log.error("修改默认收货地址失败。");
            return ApiResult.getFailEntity();
        }

        return ApiResult.getSuccessEntity(executeNum);
    }

}
