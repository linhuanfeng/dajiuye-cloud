package com.lhf.dajiuye.order.servicedubbo;

import com.lhf.dajiuye.dubbo.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component //将类放入Spring容器内
@Service(interfaceClass = UserService.class,version = "1.0.0",timeout = 15000)
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        return "hello";
    }
}
