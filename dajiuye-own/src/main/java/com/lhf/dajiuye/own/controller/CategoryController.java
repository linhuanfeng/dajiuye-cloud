package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.CommonResult;
import com.lhf.dajiuye.own.domain.LCategory;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取职位分类信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/jobcatdata")
//    @PreAuthorize("hasAuthority('user.list')")
    public Object jobcatList() {
        List<LCategory> lCategoryDataList = categoryService.getFullCategoryDataList();
        return new CommonResult<LCategory>(lCategoryDataList,new Meta("获取成功",200));
    }
}
