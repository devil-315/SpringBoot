package com.devil.helloworld.control;

import com.devil.helloworld.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：TestControl
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
//返回数据
@RestController
public class TestControl {

    //自动注入（自动创建对象）(在spring容器中找到User这个对象，用 @Component 这个注解）
    @Autowired
    private User user;

    //url http://127.0.0.1:8081  http://127.0.0.1:8081/index
    @RequestMapping(value = {"/","/index"})
    public String hello(){
        return "首页";
    }

    @RequestMapping("/user")
    public User user(){
        return user;
    }
}
