package com.devil.dao;

import com.devil.entity.pojo.UserInfo;
import com.devil.entity.query.UserInfoQuery;
import com.devil.entity.vo.ResponseVo;
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
/*
* 数据库接口层
* */
@Repository
public interface UserInfoDao {
    Integer selectCount(@Param("phone") String phone);

    Integer insert(UserInfo userInfo);

    String selectPassword(@Param("userId") Integer userId);

    Integer updateByUserId(@Param("userInfo") UserInfo userInfo);

    UserInfo getUserInfoByUserId(@Param("userId") Integer userId);

    Integer updatePassword(@Param("password") String password,@Param("userId") Integer userId);

    Integer delUser(Integer userId);

    Integer selectCounts();

    List<UserInfo> getAllUsers(@Param("pageStart") int pageStart,@Param("pageSize") Integer pageSize);

    Integer selectCountByParam(@Param("query") UserInfoQuery userInfoQuery);

    List<UserInfo> selectListParam(@Param("query") UserInfoQuery userInfoQuery);

    UserInfo selectByPhone(@Param("phone") String phone);
}
