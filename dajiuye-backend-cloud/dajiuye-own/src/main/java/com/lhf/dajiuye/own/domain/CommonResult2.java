package com.lhf.dajiuye.own.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommonResult2<T> implements Serializable {
        public T message;
        public Meta meta;
}
