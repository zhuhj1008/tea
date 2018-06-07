package com.joe.business.spring.aop;

import com.joe.business.base.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * 访问记录
 * create by Joe on 2018-05-30 11:50
 **/

@Aspect
@Component
public class ControllerLogAspect extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(* com.joe.business.commodity.controller.*.**(..))")
    public void pointCut() {
    }


//    @Before("pointCut()")
//    public void before(JoinPoint joinPoint) {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        logger.info("访问记录-访问方法名：{}", method.getName());
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String requestURI = request.getRequestURI();
//        logger.info("访问记录-访问请求地址：{}", requestURI);
//
//
//    }

    //环绕一定要有返回值，不然到了业务代码，参数就丢了
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        //开始时间
        logger.info("访问记录-开始时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("访问记录-请求方法名：{}", method.getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        logger.info("访问记录-请求资源路径：{}", requestURI);

        Object result = joinPoint.proceed();//去执行业务代码

        logger.info("访问记录-结束时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));

        return result;
    }


}
