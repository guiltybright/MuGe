package com.muge.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "muge.hello")//将springboot配置文件中s属性为gravity.hello的数据注入到当前类的属性中
public class HelloServiceProperties {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
