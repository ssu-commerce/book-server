package com.ssu.commerce.book.lock;

import com.ssu.commerce.book.annotation.DistributedLock;
import com.ssu.commerce.book.exception.DistributedLockException;
import io.micrometer.core.instrument.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DistributedLockAspect {
    private final DistributedLockUtil distributedLockUtil;

    public DistributedLockAspect(DistributedLockUtil distributedLockUtil) {
        this.distributedLockUtil = distributedLockUtil;
    }

    @Around("@annotation(distributedLock)")
    public Object applyLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockName = distributedLock.value();
        if (StringUtils.isEmpty(lockName)) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof AbstractLockableObject) {
                    AbstractLockableObject lockableObject = (AbstractLockableObject) arg;
                    lockName = lockableObject.getBookId().toString();
                    break;
                }
            }
        }

        if (StringUtils.isEmpty(lockName)) {
            throw new DistributedLockException("BOOK_003", "Lock name is empty");
        }

        boolean isLocked = distributedLockUtil.tryLock(lockName, 0);
        if (!isLocked) {
            throw new DistributedLockException("BOOK_004", "Failed to acquire lock");
        }

        try {
            return joinPoint.proceed();
        } finally {
            distributedLockUtil.unlock(lockName);
        }
    }
}

