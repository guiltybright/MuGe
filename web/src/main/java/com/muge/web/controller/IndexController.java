package com.muge.web.controller;

import com.muge.springboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    public String index() {
        return "hello world";
    }

    @RequestMapping("/hello")
    public String hello() {
        return helloService.getMessage();
    }
}
