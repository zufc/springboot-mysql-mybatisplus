package com.zfc.study.exception;

import com.zfc.study.util.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description 全局异常处理
 * @Date 2019-10-09 11:14
 * @T: MyControllerAdvice
 **/
@RestControllerAdvice
public class MyControllerAdvice {


    @ExceptionHandler(value = Exception.class)
    public ApiResult errorHandler(Exception ex){
        return new ApiResult(null,"500",ex.getMessage());
    }


    @ExceptionHandler(value = BusinessException.class)
    public ApiResult myErrorHandler(BusinessException ex){
        return new ApiResult(null,ex.getCode(),ex.getMsg());
    }

}
