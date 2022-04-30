package com.lhf.dajiuye.own.service;

import com.lhf.dajiuye.own.domain.CatItems;
import com.lhf.dajiuye.own.domain.SwiperData;

import java.util.List;

public interface SwipperDataService {
    List<CatItems> getcatItemsList() ;
    List<SwiperData> getswiperDataList() ;
}
