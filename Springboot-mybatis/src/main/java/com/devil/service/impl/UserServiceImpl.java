package com.devil.service.impl;

import com.devil.dao.UserDao;
import com.devil.pojo.User;
import com.devil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName：UserServiceImpl
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@Service //标注 这是一个业务逻辑层
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> queryUserList() {
        //查询数据
        return userDao.queryUserList();
    }

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int delUser(int id) {
        return userDao.delUser(id);
    }
}
