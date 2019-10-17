package com.zhou.javakc.config.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 微服务配置自动刷新
 * @project javakc
 * @author zhou
 * @date 2019年1月18日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigBusApplication.class, args);
    }

}
