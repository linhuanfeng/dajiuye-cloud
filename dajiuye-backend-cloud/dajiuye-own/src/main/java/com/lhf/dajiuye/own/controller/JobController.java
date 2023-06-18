package com.lhf.dajiuye.own.controller;

import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.component.ApiIdempotent;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.JobDataService;
import com.lhf.dajiuye.own.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/job")
public class JobController {

    @Resource
    private JobDataService jobDataService;

    @Autowired
    private RedisServiceImpl redisService;

    @PostMapping("/saveJob")
    @ApiIdempotent
    public Object saveJob(@ModelAttribute("job") Job job){
        jobDataService.saveJob(job);
        return "保存工作成功";
    }

    /**
     * 获取职位信息列表
     * @param jobId
     * @param params 分页数据
     * @return
     * @throws IOException
     * 参数有jobtype=    空串
     * 参数没有jobtype   null
     */
    @RequestMapping("/jobdata")
    public Object jobdata(@RequestParam(value = "jobId",required = false,defaultValue = "") String jobId,
                          Params params){
        PageInfo<Job> jobDataList = jobDataService.getJobData(jobId,params);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }

    /**
     * 增加职位热度
     * @return
     * @throws IOException
     * 参数有jobtype=    空串
     * 参数没有jobtype   null
     */
    @RequestMapping("/addJobScore")
    public Object incrementJobScore(@RequestParam("jobId") String jobId){
        redisService.incrementJobScore(jobId);
        return "增加权值成功";
    }

    /**
     * 根据id获取职位
     * @param jobId
     * @return
     */
    @GetMapping("job")
    public Object job(@RequestParam(value = "jobId") String jobId){
        Job job = jobDataService.getJobById(jobId);
        return new CommonResult2<>(job, new Meta("获取成功", 200));
    }

    /**
     * 根据id获取职位
     * @param comId
     * @return
     */
    @GetMapping("/jobByComId")
    public Object listByCom(@RequestParam(value = "comId") String comId){
        PageInfo<Job> pageInfo = jobDataService.getJobsByComId(new Params(), comId);
        return new CommonResult2<>(pageInfo, new Meta("获取成功", 200));
    }


    /**
     * 根据职位分类cid获取职位列表
     * @param params
     * @return
     * @throws IOException
     * ModelAttribute 得用 x-www-form-urlencoded格式 不能用json
     */
    @PostMapping("/jobdata2")
    public Object jobdata2(@ModelAttribute(value="QueryParams") Params params) throws IOException {
        PageInfo<Job> jobDataList = jobDataService.getJobData2(params);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }

    /**
     * 获取投递职位的反馈
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param state
     * @return
     * @throws IOException
     */
    @GetMapping("/jobsFeedback")
    public Object jobsFeedback(@RequestParam("pagenum") String pageNum,
                               @RequestParam("pagesize") String pageSize,
                               @RequestParam("userid") String userId,
                               @RequestParam("state") String state) throws IOException {
        PageInfo<Job> jobsFeedback = jobDataService.getJobsFeedback(Integer.parseInt(pageNum),Integer.parseInt(pageSize),userId, Integer.parseInt(state));
        return new CommonResult2<PageInfo>(jobsFeedback,new Meta("获取成功",200));
    }

    /**
     * 职位模糊定制
     * @param job
     * @return
     */
    @PostMapping("/jobCustom")
    public Object jobCustom(@ModelAttribute("job") Job job){
        List<Job> jobs = jobDataService.jobsCustom(job);
        return new CommonResult<Job>(jobs,new Meta("职位定制成功",200));
    }

    /**
     * 搜索模糊匹配
     * @param params
     * @return
     * @throws IOException
     */
    @RequestMapping("/jobsearch")
    public Object qSearch( Params params) throws IOException {
        PageInfo<Job> jobDataList = jobDataService.query(params);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }
}
