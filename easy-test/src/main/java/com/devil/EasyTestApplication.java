package com.devil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.devil.dao")
public class EasyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyTestApplication.class, args);
    }

}
