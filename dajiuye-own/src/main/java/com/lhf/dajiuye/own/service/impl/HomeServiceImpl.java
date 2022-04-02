package com.lhf.dajiuye.own.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.mapper.JobDataMapper;
import com.lhf.dajiuye.own.mapper.SwiperDataMapper;
import com.lhf.dajiuye.own.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SwiperDataMapper swiperDataMapper;

    @Autowired
    private JobDataMapper jobDataMapper;

    @Autowired
    private RedisServiceImpl redisService;

    /**
     * 获取轮播图数据
     * @return
     * @throws IOException
     */
    @Override
    public List<SwiperData> getswiperDataList() throws IOException {
        List<SwiperData> swiperDataList = swiperDataMapper.getSwiperDataList();
        return swiperDataList;
    }

    /**
     * 获取分类导航栏数据
     * @return
     * @throws IOException
     */
    @Override
    public List<CatItems> getcatItemsList() throws IOException {
        List<CatItems> catItemsList = swiperDataMapper.getcatItemsDataList();
        return catItemsList;
    }

    public void saveJob(Job job){
        job.setJobId(UUID.randomUUID().toString().replace("-",""));
        job.setJobReleaseTime(new Date());
        jobDataMapper.saveJob(job);
    }

    /**
     * 获取职位信息
     * @return
     */
    @Override
    public PageInfo<Job> getJobData(String jobId,Params params) {

        Integer pageNo = params.getPageNo();
        Integer pageSize = params.getPageSize();
        // 这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录
        PageHelper.startPage(pageNo,pageSize);

        List<Job> jobDataList = jobDataMapper.getJobDataList(jobId,params);

        List<Job> hotJobs = redisService.getHotJobs(params);

        // 去重,尾加到hotJobs中
        hotJobs=handleResult(hotJobs,jobDataList);

        PageInfo<Job> pageInfo = new PageInfo<>(hotJobs);

        return pageInfo;
    }

    private List<Job> handleResult(List<Job> hotJobs, List<Job> jobDataList) {
        if(hotJobs==null||hotJobs.isEmpty()){
            // hotJobs表示缓存没有，返回数据库的就行jobDataList
            return jobDataList;
        }
        Map<String,String> map=new HashMap<>();
        hotJobs.forEach(job->map.put(job.getJobId(),"1"));
        jobDataList.forEach(job -> {
            if(map.get(job.getJobId())==null){
                // hotJob中不存在，就加入
                hotJobs.add(job);
            }
        });
        return hotJobs;
    }


    /**
     * 获取职位信息2
     * @return
     */
    @Override
    public PageInfo<Job> getJobData2(Params params) {

        Integer pageNo = params.getPageNo();
        Integer pageSize = params.getPageSize();
        // 这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录
        PageHelper.startPage(pageNo,pageSize);

        List<Job> jobDataList = jobDataMapper.getJobDataList2(params);
        PageInfo<Job> pageInfo = new PageInfo<>(jobDataList);

        return pageInfo;
    }

    /**
     * 根据id获取职位信息 使用redis缓存
     * @return
     */
    @Override
    public Job getJobById(String jobId) {
        Job job = redisService.getJobRedisById(jobId);
        if(job==null){
            job = jobDataMapper.getJobById(jobId);
            redisService.saveJob(job);
        }
        return job;
    }


    /**
     * 查询投递职位反馈
     * @param userId
     * @param state
     * @return
     */
    public PageInfo<Job> getJobsFeedback(int pageNum, int pageSize,String userId,int state){
        PageHelper.startPage(pageNum,pageSize);
        List<Job> jobsFeedback = jobDataMapper.getJobsFeedback(userId, state);
        PageInfo<Job> pageInfo=new PageInfo<>(jobsFeedback);
        return pageInfo;
    }

    /**
     * 职位定制
     * @return
     */
    public List<Job> jobsCustom(Job job) {
        List<Job> jobs = jobDataMapper.jobCustom(job);
        return jobs;
    }

    /**
     * 职位模糊匹配
     * @param params
     * @param params
     * @return
     */
    @Override
    public PageInfo<Job> query(Params params) {
        Integer pageNo = params.getPageNo();
        Integer pageSize = params.getPageSize();
        // 这段代码表示，程序开始分页了，page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录
        PageHelper.startPage(pageNo,pageSize);

        List<Job> jobDataList = jobDataMapper.qSearch(params);
        PageInfo<Job> pageInfo = new PageInfo<>(jobDataList);

        return pageInfo;
    }

    /**
     * 获取公司信息
     * @return
     */
    @Override
    public List<Company> getComData(String comId) {
        List<Company> comDataList = jobDataMapper.getComDataList(comId);
        return comDataList;
    }
}
