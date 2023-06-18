package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.LCategory;
import com.lhf.dajiuye.own.domain.MCategory;
import com.lhf.dajiuye.own.domain.SCategory;
import com.lhf.dajiuye.own.mapper.CategotyMapper;
import com.lhf.dajiuye.own.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategotyMapper mapper;

    /**
     * 获取大分类列表
     * @return
     */
    @Override
    public List<LCategory> getLargeDataList() {
        List<LCategory> lCatDataList = mapper.getLargeCatDataList();
        return lCatDataList;
    }

    /**
     * 获取中分类列表
     * @return
     */
    @Override
    public List<MCategory> getMiddleDataList() {
        List<MCategory> mCatDataList = mapper.getMiddleCatDataList();
        return mCatDataList;
    }

    /**
     * 获取小分类列表
     * @return
     */
    @Override
    public List<SCategory> getSmallDataList() {
        List<SCategory> sCatDataList = mapper.getSmallCatDataList();
        return sCatDataList;
    }

    /**
     * 获取大中小列表
     * @return
     */
    @Override
    public List<LCategory> getFullCategoryDataList() {
        List<LCategory> lCatList=mapper.getLargeCatDataList();
        List<MCategory> mCatDataList = mapper.getMiddleCatDataList();
        List<SCategory> sCatDataList=mapper.getSmallCatDataList();

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
