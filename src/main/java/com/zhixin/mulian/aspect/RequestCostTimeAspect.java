package com.zhixin.mulian.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RequestCostTimeAspect {

    @Pointcut("execution(public * com.zhixin.mulian..*.*Controller.*(..))")
    public void requestCostTimeAspect() {

    }

    @Around("requestCostTimeAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long costTime = System.currentTimeMillis() - beginTime;

        String className = point.getTarget().getClass().getName();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String funcName = methodSignature.getMethod().getName();

        log.info(className + "." + funcName + " - COST TIME: " + costTime);

        return result;
    }
}
