package com.midea.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.midea.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
@EnableCaching
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate")
@Slf4j
public class CacheConfig {
    /**
     * 缓存超时时间（秒）
     */
    @Value("${spring.cluster.redis.expire:1800}")
    protected long defaultExpireTime;

    /**
     * 缓存key的前缀
     */
    @Value("${spring.cluster.cache.prefix:ccs}")
    protected String prefix;

    /**
     * 缓存管理器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);

        RedisPrefix redisPrefix = new RedisPrefix (prefix) ;
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(defaultExpireTime)) //设置默认超过期时间
                .computePrefixWith(redisPrefix)  // 设置缓存key的前缀
                .serializeValuesWith(pair);
        // configuration.computePrefixWith(cacheName -> prefix + cacheName) ;
        log.info("过期时间:{}",defaultExpireTime);
        log.info("缓存key的前缀:{}",prefix );
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

    /**
     * 配置数据源
     * @param factory
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
       // redisTemplate.opsForValue().set("s","ww",defaultExpireTime);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 键序列化器设置
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 值序列化器设置
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 加载 redis lock
     * 当system.cluster-lock.enable = true的时候才能加载redis lock
     * @return
     */
  /*  @Bean
    @ConditionalOnClass(name = "org.aspectj.lang.ProceedingJoinPoint")
    @ConditionalOnProperty(name = "system.cluster-lock.enable", havingValue = "true")
    public RedisLockAware redisLockAware() {
        return new RedisLockAware();
    }
*/
    @Bean
    public RedisUtils redisUtils (){
        return new RedisUtils() ;
    }
}
