package com.devil.service;

import com.devil.pojo.User;

import java.util.List;

/**
 * ClassName：Userservice
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
//业务逻辑层
public interface UserService {
    //查询所有的用户信息
    List<User> queryUserList();

    //根据 id 查询用户
    User queryUserById(int id);

    //新增用户
    int addUser(User user);

    //删除用户
    int delUser(int id);
}
