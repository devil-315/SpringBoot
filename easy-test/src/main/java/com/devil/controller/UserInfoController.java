package com.devil.controller;

import com.devil.entity.dto.SessionWebUserDto;
import com.devil.entity.enums.ResponseCodeEnum;
import com.devil.entity.enums.VerifyRegexEnums;
import com.devil.entity.pojo.UserInfo;
import com.devil.entity.query.UserInfoQuery;
import com.devil.entity.vo.PaginationResultVo;
import com.devil.entity.vo.ResponseVo;
import com.devil.exception.BusinessException;
import com.devil.service.UserInfoService;
import com.devil.utils.StringTools;
import com.devil.utils.VerifyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ClassName：UserController
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@RestController
@Slf4j //日志注解
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //测试一下
    @RequestMapping("/test")
    public ResponseVo test(){
        return ResponseVo.ok();
    }

    //url: 127.0.0.1:9091/api/userInfo/saveUser
    @RequestMapping("/saveUser")
    //@RequestBody 用来接收前端传递给后端的数据
    public ResponseVo saveUser(@RequestBody UserInfo userInfo){
        if(StringTools.isEmpty(userInfo.getPassword())){
            throw new BusinessException("密码不能为空");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"密码不能为空","error");
        }

        if(StringTools.isEmpty(userInfo.getUserName()) ||userInfo.getUserName().length() > 10){
            throw new BusinessException("姓名不能为空，且长度不能超过10");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"姓名不能为空，且长度不能超过10","error");
        }

        if(userInfo.getBirthday() == null){
            throw new BusinessException("出生日期不能为空");
            // return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"出生日期不能为空","error");
        }

        if(StringTools.isEmpty(userInfo.getPhone())|| !VerifyUtils.verify(VerifyRegexEnums.PHONE,userInfo.getPhone())){
            throw new BusinessException("手机号不能为空，且格式不正确");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"手机号不能为空，且格式正确","error");
        }

        if(userInfo.getSex() == null){
            throw new BusinessException("性别不能为空");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"性别不能为空","error");
        }

        if(userInfo.getPosition() == null){
            throw new BusinessException("职位不能为空");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"职位不能为空","error");
        }

        if(StringTools.isEmpty(userInfo.getRoles())){
            throw new BusinessException("角色不能为空");
            //return ResponseVo.build(ResponseCodeEnum.CODE_600.getCode(),"角色不能为空","error");
        }

        //新增
        ResponseVo responseVo =userInfoService.saveUser(userInfo);

        return responseVo;
    }

    //修改密码
    @PostMapping("/updatePassword")
    public ResponseVo updatePassword(@RequestParam("password") String password,
                                     @RequestParam("userId") Integer userId){
        //1.判空
        if(StringTools.isEmpty(password)){
            throw new BusinessException("密码不能为空");
        }
        if(userId == null){
            throw new BusinessException("用户ID不能为空");
        }

        //2.判断用户存不存在
        UserInfo userInfo =userInfoService.getUserInfoByUserId(userId);
        if(userInfo == null){
            throw new BusinessException("用户不存在");
        }

        //3.修改密码
        ResponseVo responseVo=userInfoService.updatePassword(password,userId);
        return responseVo;
    }

    //删除
    /*
    * 127.0.0.1:9091/api/userInfo/delUser/{userId}
    * */
    @PostMapping("/delUser/{userId}")
    public ResponseVo delUser(@PathVariable("userId") Integer userId){
        //1.判空
        if(userId == null){
            throw new BusinessException("用户ID不能为空");
        }
        //2.查询用户存不存在
        UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
        if(userInfo == null){
            throw new BusinessException("用户不存在");
        }
        //3.删除
        ResponseVo responseVo = userInfoService.delUser(userId);
        return responseVo;
    }

    /**
     * //todo 分页查询  127.0.0.1:9091/api/userInfo/loadDataList/页码/页大小
     * @param pageNum 页码
     * @param pageSize  页大小
     * @return
     */
    @RequestMapping(value = {"/loadDataList","/loadDataList/{pageNum}","/loadDataList/{pageNum}/{pageSize}"})
    public ResponseVo loadDataList(@PathVariable(value = "pageNum",required = false) Integer pageNum,
                                   @PathVariable(value = "pageSize",required = false) Integer pageSize){
        if(pageNum == null||pageNum == 0|| pageNum < 0){
            pageNum = 1;
        }
        if(pageSize == null || pageSize <= 0){
            pageSize = 5;
        }
        //分页
        PaginationResultVo<UserInfo> resultVo = userInfoService.findListByPage(pageNum,pageSize);
        return ResponseVo.ok(resultVo);
    }

    /**
     * //TODO 分页模糊查询
     * @Param
     * @return
     */
    @RequestMapping("/loadDataListOption")
    public ResponseVo loadDataListOption(@RequestBody(required = false) UserInfoQuery userInfoQuery){
        PaginationResultVo<UserInfo> resultVo = userInfoService.findListByQuery(userInfoQuery);
        return ResponseVo.ok(resultVo);
    }


    /*
    * 只要没登录，其他的接口，或者某些接口不能访问，只要访问，就提示，请先登录
    * */

    @PostMapping("/login")
    private ResponseVo login(@RequestParam("password") String password,
                             @RequestParam("phone") String phone,
                             HttpSession session){
        if(StringTools.isEmpty(password)){
            throw new BusinessException("密码不能为空");
        }
        if (StringTools.isEmpty(phone) || !VerifyUtils.verify(VerifyRegexEnums.PHONE,phone)){
            throw new BusinessException("手机号不能为空，或者格式不正确");
        }

        SessionWebUserDto sessionWebUserDto = userInfoService.login(phone,password);

        //设置Session
        session.setAttribute("session_key",sessionWebUserDto);

        return ResponseVo.ok(sessionWebUserDto);
    }


    //退出
    @RequestMapping("/logout")
    public ResponseVo logout(HttpSession session){
        session.invalidate();
        return ResponseVo.ok();
    }
}
