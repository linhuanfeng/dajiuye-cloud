package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolDataMapper {

    /**
     * 获取校招实体信息
     * @param schId
     * @return
     */
    List<School> getSchoolDataList(int schId);

    /**
     * 获取省份信息
     * @return
     */
    List<Province> getProvinceDataList();

    /**
     * 获取市信息
     * @return
     */
    List<City> getCityDataList();

    /**
     * 获取县信息
     * @return
     */
    List<District> getDistrictDataList();

    /**
     * 获取大分类信息
     * @return
     */
    List<LCategory> getLargeCatDataList();

    /**
     * 获取中分类信息
     * @return
     */
    List<MCategory> getMiddleCatDataList();

    /**
     * 获取小分类信息
     * @return
     */
    List<SCategory> getSmallCatDataList();
}
