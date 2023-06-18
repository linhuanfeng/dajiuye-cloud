package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.LCategory;
import com.lhf.dajiuye.own.domain.MCategory;
import com.lhf.dajiuye.own.domain.SCategory;

import java.util.List;

public interface CategoryService {
    /**
     * 获取大分类信息列表
     * @return
     */
    List<LCategory> getLargeDataList();

    /**
     * 获取中分类列表
     * @return
     */
    List<MCategory> getMiddleDataList();

    /**
     * 获取小分类信息列表
     * @return
     */
    List<SCategory> getSmallDataList();

    /**
     * 获取大中小信息列表
     * @return
     */
    List<LCategory> getFullCategoryDataList();
}
