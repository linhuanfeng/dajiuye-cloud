package com.lhf.dajiuye.seckill.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HUException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code = 500;
    private String msg;
}
