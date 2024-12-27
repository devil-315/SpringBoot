package com.devil.entity.query;

import lombok.Data;

/**
 * ClassName：BaseParam
 *
 * @author: Devil
 * @Date: 2024/9/22
 * @Description:
 * @version: 1.0
 */
@Data
public class BaseParam {
    private SimplePage simplePage;

    //当前页
    private Integer pageNo;

    //每页显示数量
    private Integer pageSize;
}
