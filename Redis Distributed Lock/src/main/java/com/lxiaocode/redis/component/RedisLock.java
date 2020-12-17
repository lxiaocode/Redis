package com.lxiaocode.redis.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author lixiaofeng
 * @date 2020/12/17 下午8:06
 * @blog http://www.lxiaocode.com/
 */
@Component
public class RedisLock {

    // 重试时间
    private final long TRY_TIME = 1000;
    // 过期时间
    private final long TIMEOUT = 5 * 1000;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取锁
     * @param key key
     * @param userId user id
     * @return true/false
     */
    public boolean lock(String key, String userId) {
        // 计算key
        String lockKey = getLockKey(key);

        // 尝试获取锁，失败重试 1 秒
        long retry = System.currentTimeMillis() + TRY_TIME;
        while (System.currentTimeMillis() < retry) {
            if (stringRedisTemplate.opsForValue().setIfAbsent(lockKey, userId, TIMEOUT, TimeUnit.MILLISECONDS)) {
                return true;
            }
            try {
                // 获取失败，1 毫秒后重试
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 释放锁
     * @param key key
     * @param userId user id
     */
    public void release(String key, String userId) {
        // 计算key
        String lockKey = getLockKey(key);

        try {
            // 获取锁的值，判断是否匹配
            String value = stringRedisTemplate.opsForValue().get(lockKey);
            if(!StringUtils.isEmpty(value) && value.equals(userId)) {
                stringRedisTemplate.opsForValue().getOperations().delete(lockKey);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLockKey(String key) {
        return "lock:" + key;
    }
}
