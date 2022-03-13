package com.lhf.dajiuye.own.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SCategory {
    Integer id;
    String sName;
    String sDesc;

    @JsonProperty("mId")
    Integer mId;
}
