package com.spring.boot.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yuderen
 * @version 2019/8/15 9:45
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.spring.boot")
@MapperScan("com.spring.boot.notify")
public class NotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifyApplication.class, args);
    }

}
