package com.lhf.dajiuye.own.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信登录后返回的用户加密信息
 */
@Data
@NoArgsConstructor
public class LoginInfo {
    String code;
    String encryptedData;
    String iv;
}
