package com.devil.interceptor;

import com.devil.entity.dto.SessionWebUserDto;
import com.devil.exception.BusinessException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName：LoginHandlerInterceptor
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
//登录拦截
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        //获取session
        SessionWebUserDto loginUser =(SessionWebUserDto) session.getAttribute("session_key");

        if(loginUser != null){
            return true;
        }
        throw new BusinessException("请先登录");

    }
}
