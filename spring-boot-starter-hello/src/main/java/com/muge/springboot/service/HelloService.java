package com.muge.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
