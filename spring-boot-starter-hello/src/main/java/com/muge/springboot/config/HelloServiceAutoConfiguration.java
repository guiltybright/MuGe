package com.muge.springboot.config;

import com.muge.springboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)//开启属性配置
@ConditionalOnClass(HelloService.class)//如果在类路径下存在HelloService才进行自动配置
@ConditionalOnProperty(prefix = "muge.hello", value = "enabled", matchIfMissing = true)//总开关
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties helloServiceProperties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class)//bean不存在则进行创建
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setMessage(helloServiceProperties.getMessage());
        return helloService;
    }
}
