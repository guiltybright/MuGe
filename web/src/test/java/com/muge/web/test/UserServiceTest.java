package com.muge.web.test;

import com.muge.web.App;
import com.muge.web.entity.User;
import com.muge.web.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void save() {
        User user = new User();
        user.setUserName("rbgdrgrdg");
        user.setPassword("123456");
        user.setAge(24);
        user.setAddress("成都");
        this.userService.save(user);
    }
}
