package com.devil.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName：SessionWebUserDto
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionWebUserDto {
    /*
    * 用户id
    * */
    private Integer userId;

    /*
    * 用户名
    * */
    private String userName;

    /*
    * 性别
    * */
    private Integer sex;
}
