package com.lhf.dajiuye.seckill.exception;

/**
 * 错误码和错误信息定义枚举类
 */
public enum  BizCodeEnum {
    UnKNOWN_ERROR(00001,"系统未知异常"),
    ACCESS_DENIED_EXCEPTION(00503,"权限不够"),
    ARITHMETIC_EXCEPTION(00002,"数学运算异常"),
    AUTH_USERNAME_PASSWORD_EXCEPTION(00003,"用户名或密码错误异常"),
    SQL_INJECT_EXCEPTION(00004,"sql包含非法字符异常"),
    TOKEN_FAIL_EXCEPTION(00005,"token格式失败异常"),
    TOKEN_EXPIRE_EXCEPTION(00006,"token过期异常"),
    SECKILL_FAIL_EXCEPTION(00007,"秒杀失败");
    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
