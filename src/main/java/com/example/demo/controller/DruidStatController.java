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

    @GetMapping("/sysdic/primary")
    public SysDic getPrimaryObj() {
        return sysDicService.findByIdA(3);
    }

    @GetMapping("/sysdic/secondary")
    public SysDic getSecondaryObj2() {
        return sysDicService.findByIdA(3);
    }
}
