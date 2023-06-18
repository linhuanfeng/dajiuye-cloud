package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.*;
import java.util.List;

public interface SchoolService {

    /**
     * 获取校招列表数据
     * @param schId
     * @return
     */
    List<School> getSchoolDataList(int schId);
}
