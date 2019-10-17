package com.zhou.javakc.system.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-07 13:59
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.zhou.javakc.component.data.entity.*")
@ComponentScan(basePackages={"com.zhou.javakc.component.data.entity.config", "com.zhou.javakc.system.user"})
public class SystemUserApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","dev");
        SpringApplication.run(SystemUserApplication.class, args);
    }

}
