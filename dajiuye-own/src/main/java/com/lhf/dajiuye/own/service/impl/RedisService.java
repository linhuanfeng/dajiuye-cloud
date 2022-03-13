package com.lhf.dajiuye.own.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhf.dajiuye.own.domain.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final ObjectMapper MAPPER=new ObjectMapper();

    /**
     * 从redis中获取数据
     * @param jobId
     * @return
     */
    public Job getJobRedis(String jobId){
        Object job = redisTemplate.opsForValue().get("job:id:" + jobId);
        if(job!=null){
            try {
                return this.MAPPER.readValue((String) job,Job.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void saveJob(Job job) {
        // 1.序列化为JSOn
        String json = null;
        try {
            json = this.MAPPER.writeValueAsString(job);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 2.存入redis
        redisTemplate.opsForValue().set("job:id:"+job.getJobId(),json,24, TimeUnit.HOURS);
    }
}
