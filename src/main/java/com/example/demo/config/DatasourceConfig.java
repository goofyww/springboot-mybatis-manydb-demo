package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.demo.comm.DataSources;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean(name = DataSources.PRIMARY)
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = DataSources.SECONDARY)
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource dataSourceSlave() {
        return DruidDataSourceBuilder.create().build();
    }

}

