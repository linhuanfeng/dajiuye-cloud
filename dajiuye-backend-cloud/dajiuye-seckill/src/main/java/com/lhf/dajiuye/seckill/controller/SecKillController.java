package com.lhf.dajiuye.seckill.controller;

import com.lhf.dajiuye.seckill.entity.SecLessonEntity;
import com.lhf.dajiuye.seckill.service.SecKillService;
import com.lhf.dajiuye.seckill.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sec")
public class SecKillController {

    @Autowired
    SecKillService secKillService;

    @Autowired
    RedisTemplate redisTemplate;

    // 秒杀下单
    @GetMapping("/current")
    public R item(){
        List<SecLessonEntity> secLessonEntities = secKillService.curSec();
        return R.ok().setData(secLessonEntities);
    }

    // 秒杀下单
    @GetMapping("/item")
    public String item(@RequestParam("userId")Long userId,
                       @RequestParam("skuId")Long skuId,
                       @RequestParam("secToken")String secToken){
        secKillService.secItem(userId, skuId, secToken);
        return "秒杀成功";
    }
}
