package com.lhf.dajiuye.own.service;

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

    void saveMessage(Message msg);

    List<Message> getAllMessage(String openId);

    List<MessageDetail> getAllMessageDetail(String openId);

    /**
     * 用户发送简历
     * @param userdeliver
     */
    void saveDeliver(Userdeliver userdeliver);

    /**
     * 更新简历投递状态
     * @param userdeliver
     */
    void updateDeliver(Userdeliver userdeliver);
}
