package com.example.demo.service.impl;

import com.example.demo.domain.SysDic;
import com.example.demo.primary.dao.SysDicMapper;
import com.example.demo.service.SysDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysDicServiceImpl implements SysDicService {

    @Autowired
    private SysDicMapper sysDicMapperA;

    @Autowired
    private com.example.demo.secondary.dao.SysDicMapper sysDicMapperB;

    @Override
    public SysDic findByIdA(Integer id) {
        log.info("primary 数据源执行");
        return sysDicMapperA.selectByPrimaryKey(id);
    }

    @Override
    public SysDic findByIdB(Integer id) {
        log.info("secondary 数据源执行");
        return sysDicMapperB.selectByPrimaryKey(id);
    }

}
