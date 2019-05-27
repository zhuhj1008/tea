package com.joe.controller;


import com.joe.dto.ApiParameter;
import com.joe.dto.ApiResult;
import com.joe.dto.wx.UnifiedParam;
import com.joe.dto.wx.WePayResult;
import com.joe.dto.wx.WxAuthParam;
import com.joe.dto.wx.WxLoginDto;
import com.joe.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"微信接口"})
@RestController
@RequestMapping("/wx")
public class WXController {

    @Autowired
    private WxService wxService;


    /**
     * 微信授权
     */
    @PostMapping("/wxAuthorization")
    @ApiOperation(value = "微信授权", notes = "微信授权")
    public ApiResult wxLoginAuthorization(@RequestBody ApiParameter<WxAuthParam> authParam) {

        WxLoginDto wxLoginDto = wxService.wxLoginAuthorization(authParam.getBody().getCode());
        return ApiResult.getSuccessEntity(wxLoginDto);
    }

    /**
     * 微信统一支付
     */
    @PostMapping("/wePayUnified")
    @ApiOperation(value = "订单微信支付", notes = "微信统一支付")
    public ApiResult wePayUnifiedOrder(@RequestBody ApiParameter<UnifiedParam> apiParameter) {

        UnifiedParam param = apiParameter.getBody();
        log.info("微信支付-统一支付，请求参数：{}。", param);

        Object o = wxService.wePayUnifiedOrder(param);
        return ApiResult.getSuccessEntity(o);
    }

//    /**
//     * 微信回调接口
//     */
//    @RequestMapping("/wxResend")
//    @ApiOperation(value = "微信回调接口", notes = "微信回调通知")
//    public void wxResend(HttpServletRequest request) throws IOException {
//        InputStream inputStream;
//        StringBuffer sb = new StringBuffer();
//        inputStream = request.getInputStream();
//        String s;
//        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//        while ((s = in.readLine()) != null) {
//            sb.append(s);
//        }
//        in.close();
//        inputStream.close();
//
//        log.info("微信回调,请求参数:" + sb);
//    }


    /**
     * 微信回调接口
     */
    @RequestMapping("/wxResend")
    @ApiOperation(value = "微信回调接口", notes = "微信回调通知")
    public void wxResend(@RequestBody WePayResult wePayResult) {

        log.info("微信回调,请求参数:" + wePayResult.toString());
    }


}


