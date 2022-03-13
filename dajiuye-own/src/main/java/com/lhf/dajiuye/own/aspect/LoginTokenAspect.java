package com.lhf.dajiuye.own.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class LoginTokenAspect {
    @Pointcut(value = "execution(public * com.lhf.dajiuye.own.controller.UserController.handleWXMsg(..))")
    public void access(){

    }
}
