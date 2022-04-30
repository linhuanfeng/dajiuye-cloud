package com.lhf.dajiuye.own.controller;

import com.lhf.dajiuye.own.domain.CommonResult;
import com.lhf.dajiuye.own.domain.Company;
import com.lhf.dajiuye.own.domain.Meta;
import com.lhf.dajiuye.own.service.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/own/com")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 获取公司信息列表
     * @return
     * @throws IOException
     */
    @RequestMapping("/comdata")
    public Object comdata(@RequestParam(value = "comId",required = false,defaultValue = "") String comId) throws IOException {
        List<Company> comDataList = companyService.getComData(comId);
        return new CommonResult<Company>(comDataList,new Meta("获取成功",200));
    }
}
