package com.lhf.dajiuye.own.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.mapper.UserDataMapper;
import com.lhf.dajiuye.own.mapper.UserDeliverMapper;
import com.lhf.dajiuye.own.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    UserDataMapper mapper;

    @Override
    public User getUserByOpenId(String openId) {
        User user = mapper.getUserByOpenId(openId);
        return user;
    }

    @Override
    public User getUserById(String Id) {
        User user = mapper.getUserById(Id);
        return user;
    }

    @Override
    public PageInfo<User> getUsers(Params2 params2){
        PageHelper.startPage(params2.getPageNo(),params2.getPageSize());
        List<User> users = mapper.getUsers(params2.getId(),params2.getState());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public int saveUser(User user) {
        int res = mapper.insertUser(user);
        return res;
    }

    @Override
    public int updateUser(User user) {
        int res = mapper.updateUser(user);
        return res;
    }

    @Override
    public int updateUserResumeByOpenId(String resumeUrl,String openId) {
        int res = mapper.updateUserResume(resumeUrl,openId);
        return res;
    }
}
