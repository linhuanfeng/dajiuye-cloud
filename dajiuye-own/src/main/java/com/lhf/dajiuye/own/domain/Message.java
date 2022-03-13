package com.lhf.dajiuye.own.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    int id;
    String fromOpenId;
    String toOpenId;
    Date createTime;
    String news;
}
