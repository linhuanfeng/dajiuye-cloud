package com.lhf.dajiuye.order.controller;

import com.lhf.dajiuye.order.service.ApiIdempotentTokenService;
import com.lhf.dajiuye.order.utils.CommonResult2;
import com.lhf.dajiuye.order.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("order/producer")
public class OrderController {

    @Autowired
    private ApiIdempotentTokenService apiIdempotentTokenService;

    /**
     * 获取分布式全局id-->雪花算法
     * @return
     * @throws IOException
     */
    @RequestMapping("/snow")
    public Object snow(){
        return new CommonResult2<String>(String.valueOf(apiIdempotentTokenService.createToken()),new Meta("获取成功",200)) ;
    }

    /**
     * 校验token
     * @return
     * @throws IOException
     */
    @RequestMapping("/check")
    public Object check(HttpServletRequest request){
        apiIdempotentTokenService.checkToken(request);
        return new CommonResult2<String>("校验成功",new Meta("获取成功",200)) ;
    }

}
