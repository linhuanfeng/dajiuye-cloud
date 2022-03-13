package com.lhf.dajiuye.own.controller;

import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.SwiperData;
import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.impl.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/home")
public class HomeController {

    @Autowired
    private HomeServiceImpl homeService;

    /**
     * 获取首页轮播图图片
     * @return
     * @throws IOException
     */
    @RequestMapping("/swiperdata")
    public Object swiperdata() throws IOException {
        List<SwiperData> swiperDataList = homeService.getswiperDataList();
        // System.out.println(swiperDataList);
        return new CommonResult<SwiperData>(swiperDataList,new Meta("获取成功",200));
    }

    /**
     * 获取分类导航图，比如校招、实习、分类、导航
     * @return
     * @throws IOException
     */
    @RequestMapping("/catitems")
    public Object catitems() throws IOException {
        List<CatItems> catItemsList = homeService.getcatItemsList();
        System.out.println(catItemsList);
        return new CommonResult<CatItems>(catItemsList,new Meta("获取成功",200));
    }

    @PostMapping("/saveJob")
    public Object saveJob(@ModelAttribute("job") Job job){
        homeService.saveJob(job);
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
//        System.out.println(params.getJobType()==null);
        PageInfo<Job> jobDataList = homeService.getJobData(jobId,params);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }

    /**
     * 根据id获取职位
     * @param jobId
     * @return
     */
    @GetMapping("job")
    public Object job(@RequestParam(value = "jobId") String jobId){
        Job job = homeService.getJobById(jobId);
        return new CommonResult2<>(job, new Meta("获取成功", 200));
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
        PageInfo<Job> jobDataList = homeService.getJobData2(params);
        System.out.println("params:"+params);
//        System.out.println(jobDataList);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }

    @GetMapping("/jobsFeedback")
    public Object jobsFeedback(@RequestParam("pagenum") String pageNum,
                               @RequestParam("pagesize") String pageSize,
                               @RequestParam("userid") String userId,
                               @RequestParam("state") String state) throws IOException {
        PageInfo<Job> jobsFeedback = homeService.getJobsFeedback(Integer.parseInt(pageNum),Integer.parseInt(pageSize),userId, Integer.parseInt(state));
        return new CommonResult2<PageInfo>(jobsFeedback,new Meta("获取成功",200));
    }

    /**
     * 搜索模糊匹配
     * @param params
     * @return
     * @throws IOException
     */
    @RequestMapping("/qsearch")
    public Object qSearch( Params params) throws IOException {
        PageInfo<Job> jobDataList = homeService.query(params);
        return new CommonResult2<PageInfo>(jobDataList,new Meta("获取成功",200));
    }

    /**
     * 职位模糊定制
     * @param job
     * @return
     */
    @PostMapping("/jobCustom")
    public Object jobCustom(@ModelAttribute("job") Job job){
        List<Job> jobs = homeService.jobsCustom(job);
        return new CommonResult<Job>(jobs,new Meta("职位定制成功",200));
    }

    /**
     * 获取公司信息列表
     * @return
     * @throws IOException
     */
    @RequestMapping("/comdata")
//    @ResponseBody
    public Object comdata(@RequestParam(value = "comId",required = false,defaultValue = "") String comId) throws IOException {
        List<Company> comDataList = homeService.getComData(comId);
        System.out.println(comDataList);
        return new CommonResult<Company>(comDataList,new Meta("获取成功",200));
    }
}
