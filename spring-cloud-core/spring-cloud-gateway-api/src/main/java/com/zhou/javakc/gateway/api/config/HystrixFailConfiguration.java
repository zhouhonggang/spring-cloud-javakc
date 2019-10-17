package com.zhou.javakc.gateway.api.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-09 16:29
 */
@RestController
public class HystrixFailConfiguration {

    @GetMapping("/hystrix/fallback")
    public String booksFallback(){
        return "服务访问超时, 请重新刷新!";
    }

    @RequestMapping("/")
    public String main()
    {
        return "这里是微服务网关服务器!";
    }

}
