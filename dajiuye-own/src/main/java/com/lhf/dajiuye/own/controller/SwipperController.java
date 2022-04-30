package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.CatItems;
import com.lhf.dajiuye.own.domain.CommonResult;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.SwiperData;
import com.lhf.dajiuye.own.service.SwipperDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/swipper")
public class SwipperController {

    @Resource
    private SwipperDataService swipperDataService;

    /**
     * 获取首页轮播图图片
     * @return
     * @throws IOException
     */
    @RequestMapping("/swiperdata")
    public Object swiperdata() throws IOException {
        List<SwiperData> swiperDataList = swipperDataService.getswiperDataList();
        // System.out.println(swiperDataList);
        return new CommonResult<SwiperData>(swiperDataList,new Meta("获取成功",200));
    }

    /**
     * 获取分类导航图，比如校招、实习、分类、导航
     * @return
     * @throws IOException
     */
    @RequestMapping("/catitems")
    public Object catitems()  {
        List<CatItems> catItemsList = swipperDataService.getcatItemsList();
        System.out.println(catItemsList);
        return new CommonResult<CatItems>(catItemsList,new Meta("获取成功",200));
    }
}
