package com.zhou.javakc.javakc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-18 14:04
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.zhou.javakc.component.data.entity.*")
@ComponentScan(basePackages={
        "com.zhou.javakc.component.data.entity.config",
        "com.zhou.javakc.component.data.redis.config",
        "com.zhou.javakc.javakc.test"
})
public class JavakcTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavakcTestApplication.class, args);
    }

}
