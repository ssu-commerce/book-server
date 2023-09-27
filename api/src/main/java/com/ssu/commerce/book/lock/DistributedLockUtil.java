package com.ssu.commerce.book.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DistributedLockUtil {

    private final RedissonClient redissonClient;

    public DistributedLockUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean tryLock(String lockName, long waitTimeSeconds) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockName);
        return lock.tryLock(waitTimeSeconds, TimeUnit.SECONDS);
    }

    public void unlock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);
        lock.unlock();
    }
}
