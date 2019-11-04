package com.zhou.javakc.component.data.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-24 08:50
 */
@SpringBootApplication
public class DataRedisApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "redis");
        SpringApplication.run(DataRedisApplication.class, args);
    }

}
