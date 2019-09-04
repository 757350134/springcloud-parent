package com.midea.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/*@Component*/
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.cluster.cache.prefix:ccs}")
    protected String prefix ;

    private String getKeyPrefix (String key) {
        Assert.notNull(prefix, "prefix must not be null");
        return prefix + ":" + key;
    }

    public RedisUtils(){
        log.info("RedisUtils");
    }


    // ============================ String =============================
    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time  时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        Assert.notNull(key, "key must not be null");
        if (time > 0) {
            redisTemplate.expire(getKeyPrefix(key), time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     * 根据key 获取过期时间
     * @param key 不能为null
     * @return
     */
    public long getExpire(String key) {
        Assert.notNull(key, "key must not be null");
        return redisTemplate.getExpire(getKeyPrefix(key), TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        Assert.notNull(key, "key must not be null");
        return redisTemplate.hasKey(getKeyPrefix(key));
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            for(int i=0; i<=key.length;i++){
                redisTemplate.delete(getKeyPrefix(key[i]));
            }
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return    值
     */
    public Object getForValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(getKeyPrefix(key));
    }


    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     */
    public void opsForValue(String key, Object value) {
        redisTemplate.opsForValue().set(getKeyPrefix(key), value);
    }

    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * 114
     */

    public void opsForValue(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(getKeyPrefix(key), value, time, TimeUnit.SECONDS);
        } else {
            opsForValue(key, value);
        }
    }


    // ============================ Hash =============================

    /**
     * Hash缓存获取
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     * 161
     */

    public Object hget(String key, String item) {
        Assert.notNull(key, "key must not be null");
        Assert.notNull(item, "item must not be null");
        return redisTemplate.opsForHash().get(getKeyPrefix(key), item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(getKeyPrefix(key));
    }

    /**
     * Hash缓存放入
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */

    public void hset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(getKeyPrefix(key), map);
    }

    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */

    public void hset(String key, Map<String, Object> map, long time) {
        hset(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key   键
     * @param item  项
     * @param value 值
     */

    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */

    public void hset(String key, String item, Object value, long time) {
        hset(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(getKeyPrefix(key), item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */

    public boolean hasKeyForHash(String key, String item) {
        return redisTemplate.opsForHash().hasKey(getKeyPrefix(key), item);
    }


    // ============================ Set =============================
    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> opsForSet(String key) {
        Assert.notNull(key, "key must not be null");
        return redisTemplate.opsForSet().members(getKeyPrefix(key));
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */

    public boolean hasKeyForSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(getKeyPrefix(key), value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */

    public long opsForSet(String key, Object... values) {
        Assert.notNull(key, "key must not be null");
        return redisTemplate.opsForSet().add(getKeyPrefix(key), values);
    }

    /**
     * 将set数据放入缓存
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */

    public long opsForSet(String key, long time, Object... values) {
        Long count = opsForSet(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */

    public long getForSetSize(String key) {
        return redisTemplate.opsForSet().size(getKeyPrefix(key));
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */

    public long removeForSet(String key, Object... values) {
        return redisTemplate.opsForSet().remove(getKeyPrefix(key), values);
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return 391
     */

    public List<Object> opsForList(String key, long start, long end) {
        return redisTemplate.opsForList().range(getKeyPrefix(key), start, end);
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */

    public long getOpsForListSize(String key) {
        return redisTemplate.opsForList().size(getKeyPrefix(key));
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */

    public Object opsForList(String key, long index) {
        return redisTemplate.opsForList().index(getKeyPrefix(key), index);
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */

    public void pushOpsForList(String key, Object value) {
        redisTemplate.opsForList().rightPush(getKeyPrefix(key), value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */

    public void pushOpsForList(String key, Object value, long time) {
        pushOpsForList(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 472
     */

    public void pushOpsForList(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(getKeyPrefix(key), value);
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */

    public void pushOpsForList(String key, List<Object> value, long time) {
        pushOpsForList(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    public void zadd(String key,Object o,int  score){
        //redisTemplate.boundZSetOps(key).
        redisTemplate.opsForZSet().add(getKeyPrefix(key),o,score);
    }

    public Set zrange(String key,long stat ,long  end){
        //redisTemplate.boundZSetOps(key).
        Set range = redisTemplate.opsForZSet().range(getKeyPrefix(key), stat, end);
        return range;
    }
}
