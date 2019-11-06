package com.zhou.javakc.eureka.ribbon;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon负载均衡
 * @project javakc
 * @author zhou
 * @date 2019年1月19日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 *  EnableEurekaClient 服务注册与发现
 *  EnableCircuitBreaker 允许断路器开启
 *  EnableHystrixDashboard 断路器监控平台
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EurekaRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * ribbon负载均衡规则
     * RandomRule 随机访问
     * RoundRobinRule 轮询方式
     * AvailabilityFilteringRule  会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数超过阈值的服务，然后对剩余的服务列表进行轮询
     * WeightedResponseTimeRule 权重 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时，如果统计信息不足，则使用轮询策略，等信息足够，切换到 WeightedResponseTimeRule
     * RetryRule 重试 先按照轮询策略获取服务，如果获取失败则在指定时间内重试，获取可用服务
     * BestAvailableRule 选过滤掉多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
     * ZoneAvoidanceRule 符合判断server所在区域的性能和server的可用性选择服务
     * @return 轮询规则
     */
    @Bean
    public IRule ribbonRule(){
        return new AvailabilityFilteringRule();
    }

}
