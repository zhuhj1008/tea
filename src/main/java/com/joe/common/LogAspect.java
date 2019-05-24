package com.joe.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * controller 请求切面
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(* com.joe.controller.*..*(..))")
    public void controllerPoint() {
        //controller切入点
    }

    @Around("controllerPoint()")
    public Object controller(ProceedingJoinPoint joinPoint) {
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求:{}-{},请求参数:{}", packageName, method, Arrays.toString(args));
        log.info("请求时间:{}", new Date());

        try {
            Object result = joinPoint.proceed();
            log.info("响应:{}-{},响应结果:{}", packageName, method, result.toString());
            log.info("响应时间:{}", new Date());
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
        }
        return null;
    }

}
