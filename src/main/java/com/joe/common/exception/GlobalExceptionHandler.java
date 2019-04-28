package com.joe.common.exception;

import com.joe.dto.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * create by Joe on 2018-07-09 14:18
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object defaultExceptionHandler(Exception e) {
        logger.info("系统异常：{}", e);
        return ApiResult.getFailEntity("操作失败，请联系管理员。");
    }

    @ExceptionHandler(value = BusinessException.class)
    public Object businessExceptionHandler(BusinessException e) {
        logger.info("业务异常：{}", e);
        return ApiResult.getFailEntity(e.getMessage());
    }

    @ExceptionHandler(value = ParameterIllegalityException.class)
    public Object paramException(ParameterIllegalityException e) {
        logger.info("请求参数异常：{}", e);
        return ApiResult.getFailEntity(e.getMessage());
    }

//    @ExceptionHandler(value = ParameterIllegalityException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST, reason = "参数缺失")
//    public void paramIllegalityException() {
//
//    }


}
