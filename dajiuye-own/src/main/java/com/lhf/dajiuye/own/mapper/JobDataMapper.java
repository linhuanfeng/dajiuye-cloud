package com.lhf.dajiuye.own.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhf.dajiuye.own.domain.Company;
import com.lhf.dajiuye.own.domain.Job;
import com.lhf.dajiuye.own.domain.Params;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JobDataMapper extends BaseMapper<Job> {

    @Insert("insert into job(jobId,jobName,jobIndustry,jobPlace,jobEdu,jobDetails,jobSalary,email,jobType,jobComId,jobReleaseTime,jobAuthorId) values("+
        "#{jobId},#{jobName},#{jobIndustry},#{jobPlace},#{jobEdu},#{jobDetails},#{jobSalary},#{email},#{jobType},#{jobComId},#{jobReleaseTime},#{jobAuthorId})")
    void saveJob(Job job);

    /**
     * 获取职位信息列表数据(有Id则查找单个)
     * 注意这里多个参数得加@Param
     * jobType:1实习 2校招 3社招
     *
     * @param
     * @return
     */
    @Select("<script>select * from job j,company c where j.jobComId=c.comId " +
            "<if test='\"\"!=jobId'>and jobId!=#{jobId} </if>" +
//            "<if test='\"\"!=#{jobId} and \"\"==#{isSimilar}'>and jobId=#{jobId} </if>" +
            "<if test='null!=params.jobType'>and j.jobType=#{params.jobType} </if>" +
            "order by jobReleaseTime desc</script>")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap") // 引用映射
    List<Job> getJobDataList(@Param("jobId") String jobId, @Param("params") Params params);

    /**
     * 根据职位分类cid返回职位列表
     * @param params
     * @return
     */
    @Select("<script>select * from job j,company c " +
            "where j.jobComId=c.comId and jobSid=#{cid} <if test=\"null!=jobType\">and jobType=#{jobType} </if> " +
            "order by jobReleaseTime desc</script>")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap") // 引用映射
    List<Job> getJobDataList2(Params params);

    /**
     * 根据公司comId返回职位列表
     * @param comId
     * @return
     */
    @Select("<script>select * from job j " +
            "where j.jobComId=#{comId} " +
            "order by jobReleaseTime desc</script>")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap") // 引用映射
    List<Job> getJobDataListByComId(String comId);

    /**
     * 根据职位分类cid返回职位列表
     * @param jobId
     * @return
     */
    @Select("select * from job j,company c where j.jobComId=c.comId and jobId=#{jobId} limit 1")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap") // 引用映射
    Job getJobById(String jobId);

    /**
     * 查询用户投递反馈
     * @return
     * @param userId
     * @param state
     */
    @Select("select d.time jobReleaseTime,j.jobName,j.jobPlace,j.jobDayPerWeek,j.jobEdu,j.jobSalary,j.jobImg,c.comMinName,c.comIndustry " +
            "from user_deliver d,job j,company c " +
            "where d.job_id=j.jobId and j.jobComId=c.comId and d.from_user_id=#{userId} and d.state=#{state}")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap")
    List<Job> getJobsFeedback(@Param("userId") String userId,@Param("state") int state);

    @Select("Select * from job j,company c where j.jobComId=c.comId and jobName like concat('%',#{query},'%') order by jobReleaseTime desc")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap")
    List<Job> qSearch(Params params);

    @Select("<script>Select * from job j,company c where j.jobComId=c.comId " +
            "<if test='null!=jobType'>and jobType=#{jobType} </if>  " +
            "<if test='\"\"!=jobPlace'>and jobPlace=#{jobPlace} </if> " +
            "<if test='\"\"!=jobName'>and jobName like concat('%',#{jobName},'%')</if> limit 15</script>")
    @ResultMap("com.lhf.dajiuye.own.mapper.JobDataMapper.jobMap")
    List<Job> jobCustom(Job job);
}
