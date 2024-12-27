package com.devil.dao;

import com.devil.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName：UserDao
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@Repository //标注这是一个数据库接口层
//@Mapper //表示这是一个 mybatis 的 mapper 类    (可以在主启动类上加注解）
public interface UserDao {
    //查询所有的用户信息
    List<User> queryUserList();

    //根据 id 查询用户
    User queryUserById(@Param("id") int id);

    //新增用户
    int addUser(User user);

    //删除用户
    int delUser(@Param("id") int id);
}
