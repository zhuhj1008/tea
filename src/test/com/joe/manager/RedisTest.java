package com.joe.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis测试类
 * create by Joe on 2018-07-11 18:52
 **/

public class RedisTest extends BaseTest{


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testA(){
//        stringRedisTemplate.opsForValue().set("name","joaaae");

        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);

    }


}
