package com.lhf.dajiuye.own.service;

//import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;

import java.util.List;

public interface MyUserService {

    User getUserByOpenId(String openId);

    User getUserById(String id);

    PageInfo<User> getUsers(Params2 params2);

    int saveUser(User user);

    int updateUser(User user);

    int updateUserResumeByOpenId(String resumeUrl, String openId);
}
