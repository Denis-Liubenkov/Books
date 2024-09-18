package com.tms.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CachingAspect {

    private final Map<String, Object> cache = new HashMap<>();

    @Around("execution(* com.tms.service.*.*(..))")
    public Object cacheMethodResult(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = generateCacheKey(joinPoint);
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            Object result = joinPoint.proceed();
            cache.put(key, result);
            return result;
        }
    }

    private String generateCacheKey(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder keyBuilder = new StringBuilder(methodName);
        for (Object arg : args) {
            keyBuilder.append(":").append(arg.toString());
        }
        return keyBuilder.toString();
    }
}
