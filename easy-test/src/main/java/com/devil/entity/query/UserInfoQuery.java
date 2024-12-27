package com.devil.entity.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ClassName：UserInfoQuery
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
/*
* Controller层接收前端的查询请求
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoQuery extends BaseParam{
    /*
     * 用户id
     * */
    private Integer userId;

    /*
     *用户名
     *  */
    private String userName;
    private String userNameFuzzy;

    /*
     * 手机号
     * */
    private String phone;
    private String phoneFuzzy;

    /*
     * 密码
     * */
    private String password;

    /*
     * 出生年月
     * */
    private Date birthday;

    /*
     * 性别 0:女 1:男
     * */
    private Integer sex;

    /*
     * 职位 0:程序员 1:测试  2:产品经理
     * */
    private Integer position;

    /*
     * 角色  0:普通用户 1:组长 2:经理 3:管理员 可多选
     * */
    private String roles;

    /*
     * 创建时间
     * */
    private Date createTime;
}
