package com.learn.aspect;


import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class HttpAspect {

    /*@Before("execution(public * com.learn.controller.StudentController.*(..))")
    public void log(){
        System.out.println(11111);
    }*/

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.learn.controller.StudentController.*(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println(11111);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        logger.info("url={}",request.getRequestURI());

        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());

        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName());

        //参数

        logger.info("args={}",joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter(){
        System.out.println(22222);
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}", object);
    }

}


