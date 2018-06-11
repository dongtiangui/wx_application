package com.spring.config;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Before("execution(* com.spring.Service.impl.Service.getUserByUserEntiry(..))")
    public void text(){

        System.out.println("前置" + Aspect.class.getName());

    }
    @After("execution(* com.spring.Service.impl.Service.getUserByUserEntiry(..)))")
    public void before(){

        System.out.println("Aspect.class.getSimpleName() = " + Aspect.class.getSimpleName());

    }

}
