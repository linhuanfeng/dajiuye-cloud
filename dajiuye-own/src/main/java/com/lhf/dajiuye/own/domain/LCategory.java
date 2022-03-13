package com.lhf.dajiuye.own.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class LCategory {
    Integer id;
    String lName;
    String lDesc;
    List<MCategory> children;
}
