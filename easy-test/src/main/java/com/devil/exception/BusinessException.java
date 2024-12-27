package com.devil.exception;

import com.devil.entity.enums.ResponseCodeEnum;

/**
 * ClassName：BusinessException
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
public class BusinessException extends RuntimeException{
    private ResponseCodeEnum responseCodeEnum;
    private Integer code;
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public ResponseCodeEnum getResponseCodeEnum() {
        return responseCodeEnum;
    }

    public void setResponseCodeEnum(ResponseCodeEnum responseCodeEnum) {
        this.responseCodeEnum = responseCodeEnum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
