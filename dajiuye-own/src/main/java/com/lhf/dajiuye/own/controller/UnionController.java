package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.CommonResult2;
import com.lhf.dajiuye.own.domain.LoginInfo;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.User;
import com.lhf.dajiuye.own.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/union")
public class UnionController {

    @Autowired
    WxService wxService;

    /**
     * 返回解密后的用户信息"nickName":"moon","gender":0,"language":"zh_CN","city":""。。。
     * @param

     * @return
     */
    @PostMapping("/handleWXMsg")
    @ResponseBody
    public Object handleWXMsg( LoginInfo loginInfo){
        User user=wxService.getUserInfo(loginInfo);
        // 新用户会被保存到数据库中，并且根据openId生成token(username也是openid,password也是openid)
        return new CommonResult2(user,new Meta("获取成功",200));
    }
}
