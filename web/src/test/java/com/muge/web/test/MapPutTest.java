package com.muge.web.test;

import com.muge.web.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * create by clive.xu on 2018/11/28
 * description:
 */
public class MapPutTest {
    public static void main(String[] args) {
        Map testMap = new HashMap<>();
        Boolean flag = false;
        testMap.put("flag", flag);
        flag = true;
        System.out.println("the flag in testMap :" + testMap.get("flag"));

        User user = new User();
        user.setUserName("张三");
        testMap.put("user", user);
        user.setUserName("李四");
        System.out.println("the user in testMap :" + ((User)testMap.get("user")).getUserName());

    }
}
