package com.joe.common.spring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器注册
 * create by Joe on 2018-08-28 17:22
 **/
@Component
public class AuthConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
//        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("").excludePathPatterns("");
        registry.addInterceptor(new ControllerInterceptor());
    }
}
