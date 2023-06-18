package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.City;
import com.lhf.dajiuye.own.domain.District;
import com.lhf.dajiuye.own.domain.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaDataMapper {
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
}
