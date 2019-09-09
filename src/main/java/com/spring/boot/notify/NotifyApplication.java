package com.spring.boot.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yuderen
 * @version 2019/8/15 9:45
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.spring.boot")
public class NotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifyApplication.class, args);
    }

}
