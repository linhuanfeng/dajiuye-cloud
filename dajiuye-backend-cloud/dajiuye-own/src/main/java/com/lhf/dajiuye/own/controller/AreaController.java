package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.CommonResult;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.domain.Province;
import com.lhf.dajiuye.own.service.AreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/area")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * 获取地点信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/placedata")
    public Object placeList() {
        List<Province> provinceDataList = areaService.getFullPlaceDataList();
        System.out.println("###############\n"+provinceDataList);
        return new CommonResult<Province>(provinceDataList,new Meta("获取成功",200));
    }
}
