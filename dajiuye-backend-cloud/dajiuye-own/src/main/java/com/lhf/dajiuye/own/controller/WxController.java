package com.lhf.dajiuye.own.controller;

import com.alibaba.fastjson.JSON;
import com.lhf.dajiuye.own.config.WxConfig;
import com.lhf.dajiuye.own.domain.CommonResult2;
import com.lhf.dajiuye.own.domain.LoginInfo;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.User;
import com.lhf.dajiuye.own.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("own/wx")
public class WxController {

    @Autowired
    WxConfig wxConfig;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    WxService wxService;

    /**
     * 根据code获取openid
     * @param
     *
     * @return
     */
    @GetMapping("/openid")
    @ResponseBody
    public Object handleWXMsg(String code){
        String url="https://"+wxConfig.getUrl()+"?appid="+wxConfig.getAppid()+"&secret="+wxConfig.getSecret()+"&js_code="+code+"&grant_type="+wxConfig.getGrantType();
        ResponseEntity<String> entity = restTemplate.getForEntity(url,String.class);
        HashMap map = JSON.parseObject(entity.getBody(), HashMap.class);
        return new CommonResult2(map.get("openid"),new Meta("获取成功",200));
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
}
