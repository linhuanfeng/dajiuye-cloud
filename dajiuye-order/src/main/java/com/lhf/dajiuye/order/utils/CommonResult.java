package com.lhf.dajiuye.order.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import scala.xml.dtd.impl.Base;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
        public List<T> message;
        public Meta meta;
}
