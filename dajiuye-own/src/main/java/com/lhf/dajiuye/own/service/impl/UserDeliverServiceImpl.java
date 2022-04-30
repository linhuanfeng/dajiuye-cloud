package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.Userdeliver;
import com.lhf.dajiuye.own.mapper.UserDeliverMapper;
import com.lhf.dajiuye.own.service.UserDeliverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDeliverServiceImpl implements UserDeliverService {

    @Resource
    private UserDeliverMapper mapper;

    @Override
    public void saveDeliver(Userdeliver userdeliver) {
        mapper.saveDeliver(userdeliver);
    }

    @Override
    public void updateDeliver(Userdeliver userdeliver) {
        mapper.updateDeliver(userdeliver);
    }
}
