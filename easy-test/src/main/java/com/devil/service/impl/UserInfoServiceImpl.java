package com.devil.service.impl;

import com.devil.dao.UserInfoDao;
import com.devil.entity.dto.SessionWebUserDto;
import com.devil.entity.enums.PageSize;
import com.devil.entity.enums.ResponseCodeEnum;
import com.devil.entity.pojo.UserInfo;
import com.devil.entity.query.SimplePage;
import com.devil.entity.query.UserInfoQuery;
import com.devil.entity.vo.PaginationResultVo;
import com.devil.entity.vo.ResponseVo;
import com.devil.exception.BusinessException;
import com.devil.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * ClassName：UserInfoServiceImpl
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
/*
* 用户信息 业务接口实现
* */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo saveUser(UserInfo userInfo) {

        /*
        * 1.通过userId 判断是新增还是修改
        * 2.新增完成或者修改完成，再去判断手机号的数量
        * 3.如果数量大于1 就报错，就回滚，不允许修改
        * */

        Integer num = 0;

        /*
         * 1.通过userId判断是新增还是修改
         * */
        //新增
        if(userInfo.getUserId() == null){
            userInfo.setCreateTime(new Date());
            num =userInfoDao.insert(userInfo);
        }else {
            //修改
            //不能修改密码
            //1.查询到这个用户原本的密码，重新赋值到userInfo中
            String password=userInfoDao.selectPassword(userInfo.getUserId());
            userInfo.setPassword(password);
            //2.修改
            num=userInfoDao.updateByUserId(userInfo);
        }


        //2.查询手机号的数量
        Integer count=userInfoDao.selectCount(userInfo.getPhone());
        if(count > 1){
            throw new BusinessException("手机号重复了");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"手机号重复了","error");
        }

        if(num > 0){
            return ResponseVo.ok();
        }
        return ResponseVo.fail();
    }

    @Override
    public UserInfo getUserInfoByUserId(Integer userId) {
        return userInfoDao.getUserInfoByUserId(userId);
    }

    @Override
    public ResponseVo updatePassword(String password, Integer userId) {
        Integer num = userInfoDao.updatePassword(password,userId);
        if(num > 0){
            return ResponseVo.ok();
        }
        throw new BusinessException("修改失败");
    }

    @Override
    public ResponseVo delUser(Integer userId) {
        Integer num = userInfoDao.delUser(userId);
        if(num > 0){
            return ResponseVo.ok();
        }
        throw new BusinessException("删除失败");
    }

    @Override
    public PaginationResultVo<UserInfo> findListByPage(Integer pageNum, Integer pageSize) {
        /*
        * 假设每页显示数量是5
        * 第一页 0，5
        * 第二页 5，5
        * 第三页 10，5
        * 起始的索引 = (pageNum - 1) * pageSize
        * */
        //每页起始索引
        int pageStart = (pageNum - 1) * pageSize;

        //总记录数
        int count = findCounts();

        //总页数
        int allTotal = (int) Math.ceil((double) count/pageSize);


        //分页
        List<UserInfo> result = userInfoDao.getAllUsers(pageStart,pageSize);

        PaginationResultVo<UserInfo> resultVo = new PaginationResultVo<>(count,pageSize,pageNum,allTotal,result);

        return resultVo;
    }

    @Override
    public PaginationResultVo<UserInfo> findListByQuery(UserInfoQuery userInfoQuery) {
        //查询总记录数
        int count = findCountByParam(userInfoQuery);

        //如果传进来的每页数量为空，默认是5，否则，就是传进来的大小
        int pageSize = userInfoQuery.getPageSize() == null ? PageSize.SIZE5.getSize() : userInfoQuery.getPageSize();

        //传入当前页，总记录数，页大小，交给SimplePage 计算 总页数和起始索引
        SimplePage simplePage = new SimplePage(userInfoQuery.getPageNo(),count,pageSize);
        userInfoQuery.setSimplePage(simplePage);

        //根据条件查询数据
        List<UserInfo> userInfos= findListParam(userInfoQuery);

        PaginationResultVo<UserInfo> resultVo = new PaginationResultVo<>(count,simplePage.getPageSize(),simplePage.getPageNo()
                ,simplePage.getPageTotal(),userInfos);
        return resultVo;
    }

    @Override
    public SessionWebUserDto login(String phone, String password) {
        //通过手机号查询用户
        UserInfo userInfo = userInfoDao.selectByPhone(phone);
        if(userInfo == null || !userInfo.getPassword().equals(password)){
            throw new BusinessException("账户或者密码错误");
        }

        SessionWebUserDto sessionWebUserDto =
                new SessionWebUserDto(userInfo.getUserId(), userInfo.getUserName(), userInfo.getSex());
        return sessionWebUserDto;
    }

    private List<UserInfo> findListParam(UserInfoQuery userInfoQuery) {
        return userInfoDao.selectListParam(userInfoQuery);
    }


    //根据参数查询数量
    private int findCountByParam(UserInfoQuery userInfoQuery) {
        return userInfoDao.selectCountByParam(userInfoQuery);
    }

    private int findCounts() {
        return userInfoDao.selectCounts();
    }
}
