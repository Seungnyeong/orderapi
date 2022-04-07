package dev.practice.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Component
@Aspect
public class logAspect {

    Logger logger = LoggerFactory.getLogger(logAspect.class);

    @Before("within (dev.practice.order.interfaces.order.OrderApiController)")
    public void beforeAdvice() {
        logger.info("Before Adivce");
    }

    @After("execution(* dev.practice.order.interfaces.order.OrderApiController.retrieveOrder(..))")
    public void afterAdvice() {
        logger.info("After Advice");
    }

    @AfterThrowing(pointcut = "execution(* dev.practice.order..*.*(..))", throwing = "e")
    public void afterThrowingAdvice(Exception e) {
        logger.error(e.getMessage());
    }

    @Around("execution(* dev.practice.order.interfaces.order.OrderApiController.retrieveOrder(..))")
    public Object time(ProceedingJoinPoint pip) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("--- Target : " + pip.getTarget());
        logger.info("--- Parameter : " + Arrays.toString(pip.getArgs()));
        Object ret = null;
        ret = pip.proceed();
        long end = System.currentTimeMillis();
        logger.info("--- Time : " + (end - start));
        return ret;
    }

}
