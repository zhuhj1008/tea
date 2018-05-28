package com.joe.manager;

import com.alibaba.fastjson.JSON;
import com.joe.app.Application;
import com.joe.config.QiNiuConfig;
import com.joe.bussiness.tools.QiNiuService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类需要实现这个类
 * create by JOE on 2018-03-14 13:18
 **/
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class)
public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QiNiuConfig qiNiuConfig;

    @Autowired
    QiNiuService qiNiuAuth;

    @Before
    public void before() {
        System.out.println("test before");
    }

    @After
    public void after() {
        System.out.println("test after");
    }

    /**
     * Simple Logging Facade for Java
     *
     */

    @Test
    public void testJSON(){
        List<String> numbers = new ArrayList<>();
        numbers.add(1+"");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");

        String s = JSON.toJSONString(numbers);
        System.out.println(s);
    }

    @Test
    public void testConfig(){
//        String accessKey = qiNiuConfig.getAccessKey();
//        System.out.println(accessKey);
        String uploadToken = qiNiuAuth.getUploadToken();
        System.out.println(uploadToken);
    }
}
