package com.joe.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Api(tags={"微信接口"})
@RestController
@RequestMapping("/weChat")
public class WeChatController {


    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/authorize")
    @ApiOperation(value = "微信授权", notes = "微信授权-未完成")
    public void authorize(@RequestParam("returnUrl") String returnUrl){

        String url = "";
//        wxMpService.oauth2buildAuthorizationUrl("",WxConsts.OAUTH2_SCOPE_USER_INFO);



    }

    @GetMapping("/userInfo")
    @ApiOperation(value = "获取微信用户信息", notes = "获取微信用户信息-未完成")
    public void userInfo(){

    }

}


