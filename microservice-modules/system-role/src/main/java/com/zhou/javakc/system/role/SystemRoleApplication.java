package com.zhou.javakc.system.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-09-06 09:00
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.zhou.javakc.component.data.entity.*")
@ComponentScan(basePackages={"com.zhou.javakc.component.data.entity.config", "com.zhou.javakc.system.role"})
public class SystemRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemRoleApplication.class, args);
    }

}