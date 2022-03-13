package com.lhf.dajiuye.own.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDetail {
    String fromOpenId;
    String fromAvatar;
    String fromName;
    String toOpenId;
    String toAvatar;
    String toName;
    String news;
    Date createTime;
}
