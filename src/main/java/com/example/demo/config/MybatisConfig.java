package com.example.demo.config;

import com.example.demo.comm.DataSources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisConfig {

    @Autowired
    @Qualifier(DataSources.PRIMARY)
    private DataSource primary;

    @Autowired
    @Qualifier(DataSources.SECONDARY)
    private DataSource secondary;

    @Autowired
    private Environment env;

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(primary);

        Map<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(DataSources.PRIMARY, primary);
        dataSourceMap.put(DataSources.SECONDARY, secondary);

        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        sqlSessionFactoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResource(env.getProperty("mybatis.config-location")));
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(env.getProperty("mybatis.mapper-locations")));
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

}
