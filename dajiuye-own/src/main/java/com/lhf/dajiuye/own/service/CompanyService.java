package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 获取公司列表信息
     * @param comId
     * @return
     */
    List<Company> getComData(String comId);
}
