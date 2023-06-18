package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.LCategory;
import com.lhf.dajiuye.own.domain.MCategory;
import com.lhf.dajiuye.own.domain.SCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategotyMapper {
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
