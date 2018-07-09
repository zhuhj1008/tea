package com.joe.business.common.exception;

import com.joe.util.mvc.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * create by Joe on 2018-07-09 14:18
 * <p>
 * 1. @RestController = @Controller + @ResponseBody     @RestControllerAdvice同理
 * 2.
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object defaultExceptionHandler(Exception e) {
        logger.info("系统异常：{}", e.getMessage());
        return ResponseEntity.getFailEntity("操作失败，请联系管理员。");
    }

    @ExceptionHandler(value = ParameterIllegalityException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST, reason = "参数缺失")
    public void paramIllegalityException() {

    }


}
