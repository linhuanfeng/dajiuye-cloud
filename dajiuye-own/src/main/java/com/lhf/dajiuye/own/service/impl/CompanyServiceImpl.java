package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.Company;
import com.lhf.dajiuye.own.mapper.CompanyMapper;
import com.lhf.dajiuye.own.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper mapper;

    @Override
    public List<Company> getComData(String comId) {
        return mapper.getComDataList(comId);
    }
}
