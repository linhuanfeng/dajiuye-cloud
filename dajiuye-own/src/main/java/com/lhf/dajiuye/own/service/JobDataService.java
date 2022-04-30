package com.lhf.dajiuye.own.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.feign.CheckTokenFeignService;

import java.io.IOException;
import java.util.List;

public interface JobDataService extends IService<Job> {

    CheckTokenFeignService getfeign();

    /**
     * 新增职位
     * @param job
     */
    void saveJob(Job job);

    /**
     * 获取就业列表信息
     * @param jobId
     * @return
     */
    PageInfo<Job> getJobData(String jobId, Params params);

    PageInfo<Job> getJobData2(Params params);

    /**
     * 获取职位信息2
     *
     * @return
     */
    PageInfo<Job> getJobsByComId(Params params,String comId) ;

    /**
     * 获取就业列表信息
     * @param jobId
     * @return
     */
    Job getJobById(String jobId);

    /**
     * 查询投递职位反馈
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param state
     * @return
     */
    PageInfo<Job> getJobsFeedback(int pageNum, int pageSize, String userId, int state);

    /**
     * 职位定制
     * @param job
     * @return
     */
    List<Job> jobsCustom(Job job);

    /**
     * 搜索职位
     * @param params
     * @return
     */
    PageInfo<Job> query(Params params);
}
