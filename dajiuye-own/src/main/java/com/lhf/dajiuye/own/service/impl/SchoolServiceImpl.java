package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.SchoolService;
import com.lhf.dajiuye.own.mapper.SchoolDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDataMapper schoolDataMapper;

    /**
     * 获取校招列表数据
     * @param schoolId
     * @return
     */
    @Override
    public List<School> getSchoolDataList(int schoolId) {
        List<School> schoolDataList = schoolDataMapper.getSchoolDataList(schoolId);
        return schoolDataList;
    }
}
