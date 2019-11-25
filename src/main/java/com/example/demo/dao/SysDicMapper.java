package com.example.demo.dao;

import com.example.demo.domain.SysDic;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDicMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysDic record);

    int insertSelective(SysDic record);

    SysDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDic record);

    int updateByPrimaryKey(SysDic record);
    
}