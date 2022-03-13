package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.LoginInfo;
import com.lhf.dajiuye.own.domain.User;

public interface WxService {
    User getUserInfo(LoginInfo loginInfo);
}
