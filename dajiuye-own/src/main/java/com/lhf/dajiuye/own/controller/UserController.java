package com.lhf.dajiuye.own.controller;

import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.aspect.OpenIdHandle;
import com.lhf.dajiuye.own.component.ApiIdempotent;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.MyUserService;
import com.lhf.dajiuye.own.service.UpLoadImgService;
import com.lhf.dajiuye.own.service.WxService;
import com.lhf.dajiuye.own.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/own/user")
@Slf4j
public class UserController {

    @Autowired
    MyUserService userService;

    /**
     * 获取openId对应的用户的所有信息
     * @param openId
     * @return
     */
    @GetMapping("/getUser")
    @OpenIdHandle
    public Object getUser(@RequestParam("openId") String openId){
        User user = userService.getUserByOpenId(openId);
        return new CommonResult2<User>(user,new Meta("获取成功",200));
    }

    /**
     * 获取所有的投递者信息
     * @param params2
     * @return
     */
    @GetMapping("/getusers")
    public Object TGetUsers(Params2 params2){
        PageInfo<User> users = userService.getUsers(params2);
        return new CommonResult2<PageInfo>(users,new Meta("获取成功",200));
    }
}
