package com.joe.common.spring.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器拦截器
 * create by Joe on 2018-08-28 17:18
 **/
public class ControllerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);


    /**
     *请求处理前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("请求处理前拦截");


        return true;
    }


    /**
     *请求处理后
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("请求返回前拦截");
    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("请求完成后拦截");
    }
}
