package com.lhf.dajiuye.own.service;

import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.*;

import java.io.IOException;
import java.util.List;

public interface HomeService {
    /**
     * 获取轮播图
     * @return
     * @throws IOException
     */
    public List<SwiperData> getswiperDataList() throws IOException;

    /**
     * 获取分类导航栏数据
     * @return
     * @throws IOException
     */
    List<CatItems> getcatItemsList() throws IOException;

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

    /**
     * 获取公司列表信息
     * @param comId
     * @return
     */
    List<Company> getComData(String comId);
}
