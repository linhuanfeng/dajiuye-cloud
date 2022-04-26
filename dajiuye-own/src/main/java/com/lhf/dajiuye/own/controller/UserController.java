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

    @Resource
    UpLoadImgService upLoadImgService;

    @Autowired
    MyUserService userService;

    @Autowired
    WxService wxService;

    /**
     * 通过fromOpenId,toOpenId,news保存用户消息
     * @param msg
     * @return
     */
    @PostMapping(value = "/saveMessage")
    public String saveMessage(@RequestBody Message msg){
        userService.saveMessage(msg);
        return "保存信息成功";
    }

    /**
     * 通过fromOpenId,userId,news保存用户消息
     * @param msg
     * @return
     */
    @PostMapping(value = "/saveMessage2")
    public String saveMessage2(@RequestBody Message msg){
        // 解谜出fromOpenId
        User user = userService.getUserById(String.valueOf(msg.getToOpenId()));
        msg.setToOpenId(user.getOpenId()); // 将id转为对应openId
        userService.saveMessage(msg);
        return "保存信息成功";
    }

    /**
     * 查询某个openId对应的所有消息
     * @param openId
     * @return
     */
    @GetMapping(value = "/getAllMessage")
//    @OpenIdHandle
    public CommonResult2 getAllMessage(@RequestParam("openId") String openId){
        List<MessageDetail> messages = userService.getAllMessageDetail(openId);
        // 这个列表只是查询到的所有信息，还得把同一个人的消息放在一个list中
        return new CommonResult2<List>(MessageUtil.mergeMsg(messages,openId),new Meta("获取成功",200));
    }

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

    /**
     * 用户发送简历
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/saveDeliver")
    public String saveDeliver(@ModelAttribute Userdeliver userdeliver){
        userService.saveDeliver(userdeliver);

        return "保存信息成功";
    }
    /**
     * 更新简历投递状态
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/updateDeliver")
    public String updateDeliver(@ModelAttribute Userdeliver userdeliver){
        userService.updateDeliver(userdeliver);
        return "保存信息成功";
    }
}
