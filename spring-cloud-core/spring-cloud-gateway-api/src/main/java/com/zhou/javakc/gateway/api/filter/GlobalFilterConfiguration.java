package com.zhou.javakc.gateway.api.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * 微服务网关自定义过滤器
 * 输出拦截请求及相关参数
 * @project javakc
 * @author zhou
 * @date 2019年1月18日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Configuration
public class GlobalFilterConfiguration {

    private Logger logger = LoggerFactory.getLogger(GlobalFilterConfiguration.class);

    /**
     * 网关前置过滤器
     * 拦截客户端请求, 输出请求相关配置信息
     * @return 放行
     */
    @Bean
    @Order(1)
    public GlobalFilter requestParseFilter() {
        return (exchange, chain) -> {
            logger.info("本次请求开始执行!");
            ServerHttpRequest serverHttpRequest = exchange.getRequest();

            HttpMethod httpMethod = serverHttpRequest.getMethod();
            logger.info("Gateway网关拦截    请求方式:"+httpMethod.name());

            String uri = serverHttpRequest.getURI().toString();
            logger.info("Gateway网关拦截    请求地址:"+uri);

            HttpHeaders httpHeaders = serverHttpRequest.getHeaders();
            logger.info("Gateway网关拦截    客户端希望接受的类型:"+httpHeaders.getAccept());
            logger.info("Gateway网关拦截    客户端发给服务器的类型:"+httpHeaders.getContentType());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("本次请求执行结束!");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter requestBodyParams() {
        return ((exchange, chain) -> {
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            }));
        });
    }

}
