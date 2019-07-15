package com.midea.model;

public class RedisLockRequest {
	
	public static final String LOCK_PREFIX = "REDIS_LOCK_";
	
	/**
     * lock key
     */
    private String lockKey;

    /**
     * 获取锁不成功时，等待时间(默认10秒)
     */
    private long timeout = 10 * 1000;

    /**
     * Redis锁自动超时时间(默认60秒)
     */
    private int expire = 60;
    
    
    public RedisLockRequest(String lockKey) {    	
        this.lockKey = LOCK_PREFIX + lockKey;
    }

    public RedisLockRequest(String lockKey, long timeout) {
    	this.lockKey = LOCK_PREFIX + lockKey;
        this.timeout = timeout;
    }

    public RedisLockRequest(String lockKey, long timeout, int expire) {
    	this.lockKey = LOCK_PREFIX + lockKey;
        this.timeout = timeout;
        this.expire = expire;
    }

	public String getLockKey() {
		return lockKey;
	}

	public long getTimeout() {
		return timeout;
	}

	public int getExpire() {
		return expire;
	}
    
}
