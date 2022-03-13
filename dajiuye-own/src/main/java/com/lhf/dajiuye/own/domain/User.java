package com.lhf.dajiuye.own.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    String id;
    String userName;
    String userPassword;
    Integer gender;
    Date birthday;
    String tempDeliverTime;
    String lastLoginIp;
    String comId;
    String nickName;
    String mobile;
    String avatar;
    String openId;
    String sessionKey;
    Integer userStatus;
    Integer isHr;
    String resume;
    String city;
    String province;
    String university;
    String qualification;
    String graduationYear;
    String honor;
    String tempJobName;
    String tempDeliverId;
    @TableField(exist = false)
    String token;
}
