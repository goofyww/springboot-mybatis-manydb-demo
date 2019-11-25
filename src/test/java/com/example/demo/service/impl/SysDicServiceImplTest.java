package com.example.demo.service.impl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.service.SysDicService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysDicServiceImplTest extends DemoApplicationTests {

    @Autowired
    private SysDicService sysDicService;

    @Test
    public void findById1Test() {
        Assert.assertEquals(sysDicService.findById1(3).getName(), "包含");
    }

    @Test
    public void findById2Test() {
        Assert.assertEquals(sysDicService.findById2(3).getName(), "包含");
    }
}