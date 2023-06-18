package com.lhf.dajiuye.own.feign;

import com.lhf.dajiuye.own.dto.LoginTokenDto;
import com.lhf.dajiuye.own.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

//@Component
@FeignClient(name = "dajiuye-acl")
public interface GetTokenByOpenIdFeignService {

    @PostMapping("/acl/user/save")
    LoginTokenDto save(UserDto userDto);

    @PostMapping("/acl/login")
    LoginTokenDto login(UserDto userDto);
}
