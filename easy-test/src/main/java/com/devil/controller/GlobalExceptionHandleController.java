package com.devil.controller;

import com.devil.entity.enums.ResponseCodeEnum;
import com.devil.entity.vo.ResponseVo;
import com.devil.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName：GlobalExceptionHandleController
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
//日志注解
@Slf4j
@RestControllerAdvice  //用于定义全局的异常处理方法
public class GlobalExceptionHandleController {
    private static final String ERROR = "error";

    //全局异常处理
    @ExceptionHandler(value = Exception.class)
    Object handException(Exception e, HttpServletRequest request){
        log.error("请求错误，请求地址：",request.getRequestURI());
        log.error("错误信息：",e);

        ResponseVo responseVo = new ResponseVo();

        //处理
        //页面不存在，404
        if(e instanceof NoHandlerFoundException){
            responseVo.setCode(ResponseCodeEnum.CODE_404.getCode());
            responseVo.setMessage(ResponseCodeEnum.CODE_404.getMeassage());
            responseVo.setStatus(ERROR);
        } else if (e instanceof BusinessException) {
            //业务异常
            responseVo.setCode(ResponseCodeEnum.CODE_600.getCode());
            responseVo.setMessage(e.getMessage());
            responseVo.setStatus(ERROR);
        }else {
            //其他异常
            responseVo.setCode(ResponseCodeEnum.CODE_500.getCode());
            responseVo.setMessage(ResponseCodeEnum.CODE_500.getMeassage());
            responseVo.setStatus(ERROR);
        }

        return responseVo;
    }
}
