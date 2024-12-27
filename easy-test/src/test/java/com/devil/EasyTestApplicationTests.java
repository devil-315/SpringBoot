package com.devil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class EasyTestApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void VerifyRegexTest(){
        //不可变
        String regex = "(1[0-9])\\d{9}$";
        //可变
        String phone = "18845697845";
        //不可变
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        System.out.println(matcher.matches());
    }

}
