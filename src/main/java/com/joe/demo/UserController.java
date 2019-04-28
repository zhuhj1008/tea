package com.joe.demo;

import com.joe.api.po.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FTL测试
 * create by Joe on 2018-08-17 18:06
 **/
@Controller
@Slf4j
public class UserController {


    @RequestMapping("/toUser")
    public String toUser(Model model) {
        model.addAttribute("name", "joe");
        return "user";
    }

    @RequestMapping("/testLog")
    public void testLog() {
        log.info("joe....");
        Config config = new Config();
        config.getEnable();
    }

}
