package com.lhf.dajiuye.own.controller;

import com.alibaba.fastjson.JSON;
import com.lhf.dajiuye.own.config.WxConfig;
import com.lhf.dajiuye.own.domain.CommonResult2;
import com.lhf.dajiuye.own.domain.LoginInfo;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.User;
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

    /**
     * 根据code获取openid
     * @param

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
}
