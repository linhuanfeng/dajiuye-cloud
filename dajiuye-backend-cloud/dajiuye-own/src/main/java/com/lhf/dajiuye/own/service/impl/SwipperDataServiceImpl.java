package com.lhf.dajiuye.own.service.impl;

import com.lhf.dajiuye.own.domain.CatItems;
import com.lhf.dajiuye.own.domain.SwiperData;
import com.lhf.dajiuye.own.mapper.SwiperDataMapper;
import com.lhf.dajiuye.own.service.SwipperDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SwipperDataServiceImpl implements SwipperDataService {

    @Autowired
    private SwiperDataMapper mapper;

    /**
     * 获取轮播图数据
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<SwiperData> getswiperDataList() {
        List<SwiperData> swiperDataList = mapper.getSwiperDataList();
        return swiperDataList;
    }

    /**
     * 获取分类导航栏数据
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<CatItems> getcatItemsList() {
        List<CatItems> catItemsList = mapper.getcatItemsDataList();
        return catItemsList;
    }
}
