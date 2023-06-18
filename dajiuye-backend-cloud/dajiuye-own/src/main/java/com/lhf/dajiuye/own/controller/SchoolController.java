package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.*;
import com.lhf.dajiuye.own.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/school/")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 获取校招图片
     * @param schId
     * @return
     * @throws IOException
     */
    @RequestMapping("/schooldata")
    public Object schoolList(@RequestParam(value = "schoolId",required = false,defaultValue = "0") String schId) throws IOException {
        List<School> schoolDataList = schoolService.getSchoolDataList(Integer.parseInt(schId));
        return new CommonResult<School>(schoolDataList,new Meta("获取成功",200));
    }
}
