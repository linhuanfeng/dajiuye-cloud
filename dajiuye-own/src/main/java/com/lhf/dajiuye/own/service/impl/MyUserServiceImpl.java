package com.lhf.dajiuye.own.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.mapper.UserDataMapper;
import com.lhf.dajiuye.own.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    UserDataMapper userDataMapper;

    @Override
    public User getUserByOpenId(String openId) {
        User user = userDataMapper.getUserByOpenId(openId);
        return user;
    }

    @Override
    public User getUserById(String Id) {
        User user = userDataMapper.getUserById(Id);
        return user;
    }

    @Override
    public PageInfo<User> getUsers(Params2 params2){
        PageHelper.startPage(params2.getPageNo(),params2.getPageSize());
        List<User> users = userDataMapper.getUsers(params2.getId(),params2.getState());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public int saveUser(User user) {
        int res = userDataMapper.insertUser(user);
        return res;
    }

    @Override
    public int updateUser(User user) {
        int res = userDataMapper.updateUser(user);
        return res;
    }

    @Override
    public int updateUserResumeByOpenId(String resumeUrl,String openId) {
        int res = userDataMapper.updateUserResume(resumeUrl,openId);
        return res;
    }

    @Override
    public void saveMessage(Message msg) {
        userDataMapper.saveMessage(msg);
    }

    @Override
    public List<Message> getAllMessage(String openId) {
        List<Message> allMessage = userDataMapper.getAllMessage(openId);
        return allMessage;
    }

    @Override
    public List<MessageDetail> getAllMessageDetail(String openId) {
        List<MessageDetail> messageDetail = userDataMapper.getAllMessageDetail(openId);
        return messageDetail;
    }

    /**
     * 用户发送简历
     * @param userdeliver
     */
    public void saveDeliver(Userdeliver userdeliver){
        userDataMapper.saveDeliver(userdeliver);
    }

    /**
     * 更新简历投递状态
     * @param userdeliver
     */
    public void updateDeliver(Userdeliver userdeliver){
        userDataMapper.updateDeliver(userdeliver);
    }
}
