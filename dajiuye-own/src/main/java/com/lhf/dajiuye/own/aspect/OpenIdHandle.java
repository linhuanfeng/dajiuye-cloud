package com.lhf.dajiuye.own.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解OpenIdHandle，标记切面，被OpenIdAspect（加密和解密openId）拦截
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenIdHandle {
    String value() default "openIdHandle";
}
