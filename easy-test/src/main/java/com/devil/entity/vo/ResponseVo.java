package com.devil.entity.vo;

import com.devil.entity.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * ClassName：ResponseVo
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@Data
public class ResponseVo<T> {
    //状态
    private String status;
    //响应码
    private Integer code;
    //响应信息
    private String message;
    //数据(一条，多条，类型可能都不一样） 使用泛型
    private T data;

    public ResponseVo() {
    }
    protected static<T>  ResponseVo<T> build(T data){
        ResponseVo<T> responseVo = new ResponseVo<>();
        if(data != null){
            responseVo.setData(data);
        }
        return responseVo;
    }

    //不传数据
    public static<T>  ResponseVo<T> build(Integer code,String message,String status){
        ResponseVo<T> responseVo = build(null);
        responseVo.setCode(code);
        responseVo.setMessage(message);
        responseVo.setStatus(status);
        return responseVo;
    }

    public static<T>  ResponseVo<T> build(T data, ResponseCodeEnum responseCodeEnum,String status){
        ResponseVo<T> responseVo = build(data);
        responseVo.setCode(responseCodeEnum.getCode());
        responseVo.setMessage(responseCodeEnum.getMeassage());
        responseVo.setStatus(status);
        return responseVo;
    }

    //成功
    public static<T>  ResponseVo<T>ok(T data){
        return build(data,ResponseCodeEnum.CODE_200,"success");
    }

    public static<T>  ResponseVo<T>ok(){
        return ok(null);
    }

    //失败
    public static<T>  ResponseVo<T>fail(T data){
        return build(data,ResponseCodeEnum.CODE_600,"error");
    }

    public static<T>  ResponseVo<T>fail(){
        return fail(null);
    }
}
