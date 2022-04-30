package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.Userdeliver;

public interface UserDeliverService {
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
