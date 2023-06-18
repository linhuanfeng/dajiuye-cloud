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
public class Params2 {
    private Integer pageSize = 10;
    private Integer pageNo = 1;
    // 面试官id
    String id;
    // 状态
    String state;
}
