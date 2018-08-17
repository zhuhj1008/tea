package com.joe.learn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * FTL测试
 * create by Joe on 2018-08-17 18:06
 **/
@Controller
public class UserController {



    @RequestMapping("/toUser")
    public String toUser(Model model){
        model.addAttribute("name","joe");
        return "user";
    }

}
