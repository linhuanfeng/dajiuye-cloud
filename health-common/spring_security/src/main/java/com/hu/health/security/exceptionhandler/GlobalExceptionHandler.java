package com.hu.health.security.exceptionhandler;

import com.hu.health.base.exception.BizCodeEnum;
import com.hu.health.base.exception.HUException;
import com.hu.health.base.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



//@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * ExceptionHandler根据最相似匹配
     * @param e
     * @return
     */

    //系统未知异常
//    @ExceptionHandler(Exception.class)
//    @ResponseBody //为了返回数据
//    public R error(Exception e) {
//        e.printStackTrace();
//        return R.error(BizCodeEnum.UnKNOWN_ERROR.getCode(),BizCodeEnum.UnKNOWN_ERROR.getMsg());
//    }

    //权限不够
    @ExceptionHandler(HUException.class)
    @ResponseBody //为了返回数据
    public R error(HUException e) {
        e.printStackTrace();
        return R.error(e.getCode(),e.getMsg()+"huhu");
    }

    //权限不够
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody //为了返回数据
    public R error(AccessDeniedException e) {
        e.printStackTrace();
        return R.error(BizCodeEnum.ACCESS_DENIED_EXCEPTION.getCode(),BizCodeEnum.ACCESS_DENIED_EXCEPTION.getMsg());
    }


    //数学运算异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error(BizCodeEnum.ARITHMETIC_EXCEPTION.getCode(),BizCodeEnum.ARITHMETIC_EXCEPTION.getMsg());
    }


}
