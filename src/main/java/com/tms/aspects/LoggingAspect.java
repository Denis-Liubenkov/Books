package com.tms.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution(* com.tms.service.*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("Exiting method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        return result;
    }

    @Around("execution(* com.tms.service.*.*(..))")
    public Object logMethodCallWithArgs(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        log.info("Arguments: {}", Arrays.toString(args));
        Object result = joinPoint.proceed();
        log.info("Result: {}", result);
        log.info("Exiting method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        return result;
    }
}



