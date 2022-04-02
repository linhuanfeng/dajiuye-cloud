package com.lhf.dajiuye.order.strategy;

/**
 * 抽象策略角色
 */
public abstract class CheckTokenStrategy {

    public abstract boolean checkToken(String token);
}
