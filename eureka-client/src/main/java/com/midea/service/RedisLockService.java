package com.midea.service;

import com.midea.model.RedisLockRequest;

public interface RedisLockService {
	
	public boolean acquireLock(RedisLockRequest redisLockRequest);
	
	public boolean releaseLock(RedisLockRequest redisLockRequest);
	
}
