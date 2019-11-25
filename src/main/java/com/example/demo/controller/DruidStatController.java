package com.example.demo.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.demo.domain.SysDic;
import com.example.demo.service.SysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DruidStatController {

    @Autowired
    private SysDicService sysDicService;

    /**
     * @return : java.lang.Object
     * @Method : druidStat
     * @Description : 该方法可以获取所有数据源的监控数据
     * @author : goofyww
     * @CreateDate : 2019-11-22 16:09:15
     */
    @GetMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    @GetMapping("/sysdic/primary")
    public SysDic getPrimaryObj() {
        return sysDicService.findById1(3);
    }

    @GetMapping("/sysdic/secondary")
    public SysDic getSecondaryObj2() {
        return sysDicService.findById2(3);
    }
}
