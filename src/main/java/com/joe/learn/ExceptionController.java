package com.joe.learn;

import com.joe.common.exception.ParameterIllegalityException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理测试
 * create by Joe on 2018-07-09 15:00
 **/

@RestController
@RequestMapping("/test")
public class ExceptionController {


    @RequestMapping("/restController")
    public Object testRestController() {
        Map map = new HashMap() {{
            put("name", "joe");
            put("age", "20");
        }};

        return map;
    }

    @RequestMapping("/exception")
    public Object testGlobalException() {
        System.out.println("要报错，返回给调用方响应体");
        return 5 / 0;
    }

    @RequestMapping("/paramException")
    public Object testParamException() {
        System.out.println("要报错，返回给前台http状态码");
        throw new ParameterIllegalityException("ada");
    }
}
