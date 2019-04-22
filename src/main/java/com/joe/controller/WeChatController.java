package com.joe.controller;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weChat")
@Slf4j
public class WeChatController {


    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/authorize")
    public void authorize(@RequestParam("returnUrl") String returnUrl){

        String url = "";
//        wxMpService.oauth2buildAuthorizationUrl("",WxConsts.OAUTH2_SCOPE_USER_INFO);



    }

    @GetMapping("/userInfo")
    public void userInfo(){

    }

}


