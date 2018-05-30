package com.joe.bussiness.spring.aop;

import com.alibaba.fastjson.JSON;
import com.joe.bussiness.base.BaseController;
import com.joe.util.mvc.RequestEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 访问记录切面
 * create by Joe on 2018-05-30 11:50
 **/

//@Aspect
//@ComponentScan
public class ControllerLogAspect extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut()
    public void pointCut() {
    }


    @Before("execution(* com.joe.bussiness.*.controller.*.* (..))")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("访问拦截-拦截方法名：{}", method.getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        logger.info("访问拦截-拦截请求地址：{}", requestURI);

        String requestParam = convertRequest2String(request);
        RequestEntity requestEntity = JSON.parseObject(requestParam, RequestEntity.class);



    }


}
