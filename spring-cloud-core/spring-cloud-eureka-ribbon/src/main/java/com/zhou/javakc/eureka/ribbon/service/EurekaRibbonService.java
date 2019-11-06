package com.zhou.javakc.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 微服务负载均衡Ribbon逻辑层
 * @project javakc
 * @author zhou
 * @date 2019年1月19日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Service
public class EurekaRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 实现熔断和服务降级
     * @return 执行结果或错误信息
     */
    @HystrixCommand(fallbackMethod = "fallBack")
    public String getName(){
        String url = "http://SYSTEM-USER/system/user";
        return restTemplate.getForObject(url, String.class);
    }

    public String fallBack(){
        return "{\"success\": false, \"message\": \"The current service is down\"}";
    }

}
