package com.zhou.javakc.component.data.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis缓存管理实现
 *
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-24 10:17
 */
@Component
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 清理redis缓存
     * @param key 名称
     */
    public void delete(final String key) {
        if (exists(key))
            redisTemplate.delete(key);
    }

    /**
     * 批量清理redis缓存
     * @param keys 名称数组
     */
    public void deletes(final String... keys) {
        for (String key : keys)
            delete(key);
    }

    /**
     * 验证redis是否存在
     * @param key 名称
     * @return 是否存在
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取指定key属性值
     * @param key 名称
     * @return value值
     */
    public Object get(final String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 写入指定key属性值
     * @param key 名称
     * @param value 值
     * @return 状态
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入指定key属性值(带超时时间)
     * @param key 名称
     * @param value 值
     * @param expireTime 超时时间 s
     * @return 状态
     */
    public boolean set(final String key, String value, long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
