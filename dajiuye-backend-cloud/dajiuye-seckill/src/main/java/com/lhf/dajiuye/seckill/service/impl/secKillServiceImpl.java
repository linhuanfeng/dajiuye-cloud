package com.lhf.dajiuye.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.health.common.to.SecOrderTo;
import com.lhf.dajiuye.seckill.constants.SecKillConstants;
import com.lhf.dajiuye.seckill.constants.SecKillMqConstants;
import com.lhf.dajiuye.seckill.dao.SecKillDao;
import com.lhf.dajiuye.seckill.entity.SecLessonEntity;
import com.lhf.dajiuye.seckill.exception.BizCodeEnum;
import com.lhf.dajiuye.seckill.exception.HUException;
import com.lhf.dajiuye.seckill.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class secKillServiceImpl extends ServiceImpl<SecKillDao, SecLessonEntity> implements SecKillService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     *
     * @param userId
     * @param skuId
     * @param secToken 藐视时间到才开始暴露，防止被内部人员先抢
     * @return
     */
    @Override
    public String secItem(Long userId,Long skuId,String secToken) {
        // 检查秒杀时间是否合法
        if(!checkTime(skuId)){
            throw new HUException(BizCodeEnum.SECKILL_FAIL_EXCEPTION.getCode(),BizCodeEnum.SECKILL_FAIL_EXCEPTION.getMsg());
        }
        // 该用户是否秒杀过该商品 set skuId:hasKill->userId
        Boolean hasKill = redisTemplate.opsForSet().isMember(SecKillConstants.SECKILL_HAS_KILL_PRE+skuId, userId.toString());
        if(hasKill){
            throw new HUException(BizCodeEnum.SECKILL_FAIL_EXCEPTION.getCode(),BizCodeEnum.SECKILL_FAIL_EXCEPTION.getMsg());
        }
        // 尝试获取信号量，或信号量不够或者令牌错误都不能成功
        RSemaphore semaphore = redissonClient.getSemaphore(SecKillConstants.SECKILL_STOCK_PRE+secToken);
        try {
            boolean isOk = semaphore.tryAcquire(1, 5, TimeUnit.MILLISECONDS);
            if(!isOk){
                // 秒杀成功
                throw new HUException(BizCodeEnum.SECKILL_FAIL_EXCEPTION.getCode(),BizCodeEnum.SECKILL_FAIL_EXCEPTION.getMsg());
            }
            // 存入set,标记已访问过
            BoundSetOperations<String, String> setOps = redisTemplate.boundSetOps(SecKillConstants.SECKILL_HAS_KILL_PRE + skuId);
            setOps.add(userId.toString());
            setOps.expire(1,TimeUnit.DAYS);
            return createSecKillOrder(userId,skuId);
        }catch (InterruptedException e){
            log.error("秒杀失败{}"+e.getMessage());
            throw new HUException(BizCodeEnum.SECKILL_FAIL_EXCEPTION.getCode(),BizCodeEnum.SECKILL_FAIL_EXCEPTION.getMsg());
        }
    }

    /**
     * 检查秒杀时间合法性和商品是否存在
     * @param skuId
     * @return
     */
    private boolean checkTime(Long skuId){
        BoundHashOperations<String, String, String> hashOps1 = redisTemplate.boundHashOps(SecKillConstants.SECKILL_SKU_INFO);
        // 判断是否有次
        SecLessonEntity entity = JSON.parseObject(hashOps1.get(skuId.toString()), SecLessonEntity.class);
        if(entity!=null&&entity.getStartTime().getTime()<=System.currentTimeMillis()&&
                entity.getEndTime().getTime()>=System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    /**
     * 获取当前可以秒杀的商品
     * @return
     */
    @Override
    public List<SecLessonEntity> curSec(){
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(SecKillConstants.SECKILL_LIST);
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SecKillConstants.SECKILL_SKU_INFO);
        List<String> list = listOps.range(0, -1);
        List<SecLessonEntity> ans=new ArrayList<>();
        list.forEach((e)->{
            String[] split = e.split("-");
            // 获取商品对象
            SecLessonEntity entity = JSON.parseObject(hashOps.get(split[0]), SecLessonEntity.class);
            String now = String.valueOf(System.currentTimeMillis());
            if(split[1].compareTo(now)>0){
                // 时间未到，token不暴露
                entity.setToken(null);
            }
            ans.add(entity);
        });
        return ans;
    }

    /**
     * 更新最近三天的秒杀信息到《hash》 seckill:sku  skuId->skuEntity
     */
    @Override
    public void updateLatest3DayLesson() {
        // 获取最近三天的所有商品list
        List<SecLessonEntity> secLessonEntities = this.list(new QueryWrapper<SecLessonEntity>().between("start_time", startTime(), endTime()));
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SecKillConstants.SECKILL_SKU_INFO);
        if(secLessonEntities!=null&&secLessonEntities.size()>0){
            secLessonEntities.forEach((e)->{
                String skuId = e.getId().toString();
                // 该商品还未上架
                if(!hashOps.hasKey(skuId)){
                    // 设置秒杀token
                    String token = UUID.randomUUID().toString().replace("-", "");
                    e.setToken(token);
                    // 上架商品
                    hashOps.put(skuId,JSON.toJSONString(e));
                    hashOps.expireAt(e.getEndTime());
                    // 设置库存信息
                    String semaphoreKey=SecKillConstants.SECKILL_STOCK_PRE+token;
                    RSemaphore semaphore = redissonClient.getSemaphore(semaphoreKey);
                    semaphore.trySetPermits(e.getStock());
                    semaphore.expireAt(e.getEndTime());
                    // 维护一个list，临时放置秒杀商品
                    BoundListOperations<String, String> listOps = redisTemplate.boundListOps(SecKillConstants.SECKILL_LIST);
                    listOps.rightPush(e.getId().toString()+"-"+e.getStartTime().getTime()+"-"+e.getEndTime().getTime());
                    listOps.expireAt(e.getEndTime());
                }
            });
        }
    }

    private String startTime() {
        LocalDate now = LocalDate.now();
        // 返回本地日期和本地时间组成的实例
        return LocalDateTime.of(now, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String endTime() {
        LocalDate afterTwoDays = LocalDate.now().plusDays(2);
        // 返回本地日期和本地时间组成的实例
        return LocalDateTime.of(afterTwoDays, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * 异步快速下单,队列削锋
     * @return
     */
    private String createSecKillOrder(Long userId,Long skuId) {
        SecOrderTo secKillOrderTo = new SecOrderTo();
        secKillOrderTo.setUserId(userId);
        secKillOrderTo.setSkuId(skuId);
        secKillOrderTo.setOrderSn(IdWorker.getTimeId());
        /**
         * String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor,
         * 			CorrelationData correlationData
         */
        rabbitTemplate.convertAndSend(SecKillMqConstants.ORDER_EVENT_EXCHANGE,
                SecKillMqConstants.ORDER_SECKILL_DEAL_ROUTINGKEY,
                secKillOrderTo);
        log.error("快速下单中");
        return "true";
    }
}
