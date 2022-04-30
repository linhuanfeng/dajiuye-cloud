package com.lhf.dajiuye.dubbo.order;

import javax.servlet.http.HttpServletRequest;

public interface CheckTokenFeignService {

    /**
     * 去order模块校验token
     * @param request
     * @return
     */
    Object check(HttpServletRequest request);
}
