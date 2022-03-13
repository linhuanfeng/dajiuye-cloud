package com.lhf.dajiuye.own.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
/**
 * 查询参数
 */
public class Params {
    private Integer pageSize = 10;
    private Integer pageNo = 0;
    // 查询的内容
    String query;
    // 分类id
    String cid;
    // 职位类型，1实习，2校招，3社招
    public Integer jobType;
}
