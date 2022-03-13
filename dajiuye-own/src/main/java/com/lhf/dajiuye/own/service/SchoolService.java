package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.*;
import java.util.List;

public interface SchoolService {

    /**
     * 获取校招列表数据
     * @param schId
     * @return
     */
    public List<School> getSchoolDataList(int schId);

    /**
     * 获取省信息列表
     * @return
     */
    List<Province> getProvinceDataList();

    /**
     * 获取市列表
     * @return
     */
    List<City> getCityDataList();

    /**
     * 获取县信息列表
     * @return
     */
    List<District> getDistrictDataList();

    /**
     * 获取省市县信息列表
     * @return
     */
    List<Province> getFullPlaceDataList();

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
