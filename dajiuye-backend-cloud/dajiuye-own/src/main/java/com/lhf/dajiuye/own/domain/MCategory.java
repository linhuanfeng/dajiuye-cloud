package com.lhf.dajiuye.own.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class MCategory {
    Integer id;
    String mName;
    String mDesc;
    Integer lId;
    List<SCategory> children;
}
