package com.midea.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Slf4j
public class RedisPrefix implements CacheKeyPrefix {

    private final RedisSerializer serializer;

    private final String delimiter;

    public RedisPrefix() {
        this(":");
    }

    public RedisPrefix(String delimiter) {
        this.serializer = new StringRedisSerializer();
        this.delimiter = delimiter;
    }

    /**
     * 设置redis缓存前缀
     *
     * @param cacheName 前缀
     * @return never {@literal null}.
     */
    @Override
    public String compute(String cacheName) {
        String bytes = this.delimiter != null ? this.delimiter.concat(":").concat(cacheName).concat(":") : cacheName.concat(":") ;
        return (String) serializer.deserialize(bytes.getBytes());
    }
}
