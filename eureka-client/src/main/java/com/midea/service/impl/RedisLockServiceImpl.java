package com.midea.service.impl;

import com.midea.model.RedisLockRequest;
import com.midea.service.RedisLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockServiceImpl implements RedisLockService {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisLockServiceImpl.class.getName());
    
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean acquireLock(RedisLockRequest redisLockRequest) {
        try {
        	long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < redisLockRequest.getTimeout()) {
                //获取锁同时设置失效时间
                Boolean isLock = redisTemplate.opsForValue().setIfAbsent(redisLockRequest.getLockKey(), formatter.format(new Date()),redisLockRequest.getExpire(),TimeUnit.SECONDS);
                if(isLock){
                    logger.debug("获取Redis锁成功: " + redisLockRequest.getLockKey());
                    return true;
                }
                Thread.sleep(1000);
            }
            throw new RuntimeException("获取Redis锁超时");
        } catch (Exception e) {
        	logger.error("获取Redis锁失败: " + redisLockRequest.getLockKey(), e);
            throw new RuntimeException("获取Redis锁失败",e);
        }
	}

	@Override
	public boolean releaseLock(RedisLockRequest redisLockRequest) {
		try {
            //if(jedisUtil.execExistsFromCache(cacheKey)){
            	return redisTemplate.delete(redisLockRequest.getLockKey()); //直接删除
            //}
        } catch (Exception e) {
        	logger.error("删除Redis锁失败: " + redisLockRequest.getLockKey(), e);
            throw new RuntimeException("删除Redis锁失败",e);
        }
	}
	
}
