package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.SchoolService;
import com.lhf.dajiuye.own.mapper.SchoolDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("schoolService")
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

    /**
     * 获取省信息列表
     * @return
     */
    @Override
    public List<Province> getProvinceDataList() {
        List<Province> provinceDataList = schoolDataMapper.getProvinceDataList();
        return provinceDataList;
    }

    /**
     * 获取市信息列表
     * @return
     */
    @Override
    public List<City> getCityDataList() {
        List<City> cityDataList = schoolDataMapper.getCityDataList();
        return cityDataList;
    }

    /**
     * 获取县信息列表
     * @return
     */
    @Override
    public List<District> getDistrictDataList() {
        List<District> districtDataList = schoolDataMapper.getDistrictDataList();
        return districtDataList;
    }

    /**
     * 获取省市县信息列表
     * @return
     */
    @Override
    public List<Province> getFullPlaceDataList() {
        List<Province> provinceList=schoolDataMapper.getProvinceDataList();
        List<City> cityDataList = schoolDataMapper.getCityDataList();
//        List<District> districtDataList=schoolDataMapper.getDistrictDataList();

//        for (District district : districtDataList) {
//            // 因为默认升序排列
//            int n=Integer.parseInt(district.getCity_id())-1;
//            if(cityDataList.get(n).getChildren()==null){
//                cityDataList.get(n).setChildren(new ArrayList<District>());
//            }
//            cityDataList.get(n).getChildren().add(district);
//        }

        for (City city : cityDataList) {
            int n=Integer.parseInt(city.getProvinceId())-1;
            if(provinceList.get(n).getChildren()==null){
                provinceList.get(n).setChildren(new ArrayList<City>());
            }
            provinceList.get(n).getChildren().add(city);
        }

        return provinceList;
    }

    /**
     * 获取大分类列表
     * @return
     */
    @Override
    public List<LCategory> getLargeDataList() {
        List<LCategory> lCatDataList = schoolDataMapper.getLargeCatDataList();
        return lCatDataList;
    }

    /**
     * 获取中分类列表
     * @return
     */
    @Override
    public List<MCategory> getMiddleDataList() {
        List<MCategory> mCatDataList = schoolDataMapper.getMiddleCatDataList();
        return mCatDataList;
    }

    /**
     * 获取小分类列表
     * @return
     */
    @Override
    public List<SCategory> getSmallDataList() {
        List<SCategory> sCatDataList = schoolDataMapper.getSmallCatDataList();
        return sCatDataList;
    }

    /**
     * 获取大中小列表
     * @return
     */
    @Override
    public List<LCategory> getFullCategoryDataList() {
        List<LCategory> lCatList=schoolDataMapper.getLargeCatDataList();
        List<MCategory> mCatDataList = schoolDataMapper.getMiddleCatDataList();
        List<SCategory> sCatDataList=schoolDataMapper.getSmallCatDataList();

        for (SCategory sCat : sCatDataList) {
            // 因为默认升序排列
//            int n=sCat.getmId()-1;
            int n=sCat.getMId()-1;
            if(mCatDataList.get(n).getChildren()==null){
                mCatDataList.get(n).setChildren(new ArrayList<SCategory>());
            }
            mCatDataList.get(n).getChildren().add(sCat);
        }

        for (MCategory mCat : mCatDataList) {
//            int n=mCat.getlId()-1;
            int n=mCat.getLId()-1;
            if(lCatList.get(n).getChildren()==null){
                lCatList.get(n).setChildren(new ArrayList<MCategory>());
            }
            lCatList.get(n).getChildren().add(mCat);
        }

        return lCatList;
    }
}
