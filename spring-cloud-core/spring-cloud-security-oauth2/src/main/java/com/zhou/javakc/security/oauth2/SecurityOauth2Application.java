package com.zhou.javakc.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableEurekaClient
@EnableAuthorizationServer
public class SecurityOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauth2Application.class, args);
	}

}
