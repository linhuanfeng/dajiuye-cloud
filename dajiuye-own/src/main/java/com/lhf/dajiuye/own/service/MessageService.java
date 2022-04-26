package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.Message;
import com.lhf.dajiuye.own.domain.MessageDetail;

import java.util.List;

public interface MessageService {
    void saveMessage(Message msg);

    List<Message> getAllMessage(String openId);

    List<MessageDetail> getAllMessageDetail(String openId);
}
