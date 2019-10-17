package com.zhou.javakc.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@EnableAuthorizationServer
public class SecurityOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauth2Application.class, args);
	}

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authenticationManager(){
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}

}
