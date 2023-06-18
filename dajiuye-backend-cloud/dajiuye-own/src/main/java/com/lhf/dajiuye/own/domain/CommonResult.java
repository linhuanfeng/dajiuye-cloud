package com.lhf.dajiuye.own.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
        public List<T> message;
        public Meta meta;
}
