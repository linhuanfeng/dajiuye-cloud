package com.lhf.dajiuye.own.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
@FeignClient(name = "dajiuye-order")
public interface CheckTokenFeignService {

    /**
     * 校验token
     * @return
     * @throws IOException
     */
    @RequestMapping("order/producer/check")
    Object check(HttpServletRequest request);
}
