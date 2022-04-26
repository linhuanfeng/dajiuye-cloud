package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.Message;
import com.lhf.dajiuye.own.domain.MessageDetail;
import com.lhf.dajiuye.own.mapper.UserDataMapper;
import com.lhf.dajiuye.own.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    UserDataMapper userDataMapper;

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
}
