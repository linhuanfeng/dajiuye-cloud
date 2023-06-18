package com.lhf.dajiuye.own.dto;

import lombok.Data;
import lombok.ToString;
import scala.Int;

import java.io.Serializable;

@Data
@ToString
public class LoginTokenDto implements Serializable {
    String msg;
    Integer code;
    String token;
}
