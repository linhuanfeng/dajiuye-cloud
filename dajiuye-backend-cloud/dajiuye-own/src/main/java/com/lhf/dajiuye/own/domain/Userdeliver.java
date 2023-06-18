package com.lhf.dajiuye.own.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户投递简历实体类
 */
@Data
public class Userdeliver {
    int id;
    String fromUserId;
    String toHrId;
    String resumeUrl;
    Date time;
    String jobId;
    int state;
    String message;
}
