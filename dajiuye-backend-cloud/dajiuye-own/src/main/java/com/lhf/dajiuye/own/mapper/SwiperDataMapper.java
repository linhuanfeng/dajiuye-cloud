package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.CatItems;
import com.lhf.dajiuye.own.domain.SwiperData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SwiperDataMapper {

    /**
     * 获取轮播图数据
     */
    @Select("select * from swiper")
    List<SwiperData> getSwiperDataList();

    /**
     * 获取分类导航栏数据
     * @return
     */
    @Select("select * from index_cate")
    List<CatItems> getcatItemsDataList();
}
