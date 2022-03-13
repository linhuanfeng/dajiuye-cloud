package com.lhf.dajiuye.own.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Job implements Serializable {
    String jobId;
    String jobSalary;
    String jobName;
    String jobPlace;
    String jobEdu;
    String jobAge;
    String jobDayPerWeek;
    String jobImg;
    String jobComId;
    String jobSid;
    Date jobUpdateTime;
    Date jobReleaseTime;
    String email;
    Integer jobType;
    String jobAuthorId;
    String jobDetails;
    String jobStat;
    String jobViewCnt;
    String jobPriority;
    String jobIndustry;
    String jobTimeSpan;
    String jobDeadLine;
    String jobWelfare;
    Company company;
}
