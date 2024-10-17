package com.example;

import com.example.modules_admin.dao.UserDao;
import com.example.modules_admin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackJavaApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        List<User> userList = new User().selectAll();
        System.out.println(userList);
    }

}
