package com.devil.config;

import com.devil.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName：MyMVCConfig
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
//配置类
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义的拦截器
        registry.addInterceptor(new LoginHandlerInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //放行登录接口
                .excludePathPatterns("/userInfo/login")
                .excludePathPatterns("/userInfo/logout");
    }
}
