package com.devil.helloworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName：User
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "user") //读取配置   prefix：添加前缀
public class User {
    private String name;
    private Integer age;
    private boolean happy;
    private Date birthday;
    private Map<String , Object> maps;
    private List<Object> list;
    private Person person;
}
