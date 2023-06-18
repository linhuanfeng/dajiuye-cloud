package com.lhf.dajiuye.order.service;

import javax.servlet.http.HttpServletRequest;

public interface ApiIdempotentTokenService {
    long createToken();
    void checkToken(HttpServletRequest request);
}
