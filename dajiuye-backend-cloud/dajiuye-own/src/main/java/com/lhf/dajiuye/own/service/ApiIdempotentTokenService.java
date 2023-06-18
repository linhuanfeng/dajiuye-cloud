package com.lhf.dajiuye.own.service;

import javax.servlet.http.HttpServletRequest;

public interface ApiIdempotentTokenService {
    long createToken();
    void checkToken(HttpServletRequest request);
}
