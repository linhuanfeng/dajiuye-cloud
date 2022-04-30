package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.Userdeliver;
import com.lhf.dajiuye.own.service.UserDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/own/deliver")
public class UserdeliverController {

    @Autowired
    UserDeliverService userDeliverService;
    /**
     * 用户发送简历
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/saveDeliver")
    public String saveDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.saveDeliver(userdeliver);

        return "保存信息成功";
    }
    /**
     * 更新简历投递状态
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/updateDeliver")
    public String updateDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.updateDeliver(userdeliver);
        return "保存信息成功";
    }
}
