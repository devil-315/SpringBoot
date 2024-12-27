package com.devil.controller;

import com.devil.dao.UserDao;
import com.devil.pojo.User;
import com.devil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName：UserController
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserList")
    public List<User> queryUserList(){
        return userService.queryUserList();
    }

    @RequestMapping("/queryUserById")
    public User queryUserById(int id){
        return userService.queryUserById(id);
    }

    @RequestMapping("/addUser")
    public String  addUser(User user){
        User user1 = new User();
        user1.setName("hahaha");
        user1.setPwd("123456");
        int i = userService.addUser(user1);
        if(i > 0){
            return "新增成功";
        }
        return "新增失败";
    }

    @RequestMapping("/delUser")
    public String  delUser(int id){
        int i = userService.delUser(id);
        if(i > 0){
            return "删除成功";
        }
        return "删除失败";
    }
}
