package com.liuhongyin.springsecurity;

import com.liuhongyin.springsecurity.mapper.UserMapper;
import com.liuhongyin.springsecurity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        User user = userMapper.findByUsername("root");
        String a = "";
    }

}
