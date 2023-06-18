package com.hu.health.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 这是因为Spring boot 2.0.3引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
 * 并且页面毫无响应。
 * 因此，需要创建PasswordEncorder的实现类。
 */
@Deprecated
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}