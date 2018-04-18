package com.ddnet.cloud.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务启动主程序
 * Created by Vinson.Ding on 2017/8/23.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@RestController
public class AccountApplication {
    @Value("${app.description}")
    private String description;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    public String mainWeb() {
        return description;
    }
}
