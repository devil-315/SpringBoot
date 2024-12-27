package com.devil.service;

import com.devil.entity.dto.SessionWebUserDto;
import com.devil.entity.pojo.UserInfo;
import com.devil.entity.query.UserInfoQuery;
import com.devil.entity.vo.PaginationResultVo;
import com.devil.entity.vo.ResponseVo;

/**
 * ClassName：UserInfoService
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
/*
 * 用户信息 业务接口实现
 * */
public interface UserInfoService {
    ResponseVo saveUser(UserInfo userInfo);

    UserInfo getUserInfoByUserId(Integer userId);

    ResponseVo updatePassword(String password, Integer userId);

    ResponseVo delUser(Integer userId);

    PaginationResultVo<UserInfo> findListByPage(Integer pageNum, Integer pageSize);

    PaginationResultVo<UserInfo> findListByQuery(UserInfoQuery userInfoQuery);

    SessionWebUserDto login(String phone, String password);
}
