package com.lhf.dajiuye.own.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class City implements Serializable {
    Integer id;
    String cityName;
    String cityAdcode;
    String provinceAdcode;
    String provinceId;
    List<District> children;
}
