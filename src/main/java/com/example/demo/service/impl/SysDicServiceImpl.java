package com.example.demo.service.impl;

import com.example.demo.annotation.RouteDataSource;
import com.example.demo.comm.DataSources;
import com.example.demo.dao.SysDicMapper;
import com.example.demo.domain.SysDic;
import com.example.demo.service.SysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDicServiceImpl implements SysDicService {

    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    @RouteDataSource(DataSources.PRIMARY)
    public SysDic findById1(Integer id) {
        return sysDicMapper.selectByPrimaryKey(id);
    }

    @Override
    @RouteDataSource(DataSources.SECONDARY)
    public SysDic findById2(Integer id) {
        return sysDicMapper.selectByPrimaryKey(id);
    }

}
