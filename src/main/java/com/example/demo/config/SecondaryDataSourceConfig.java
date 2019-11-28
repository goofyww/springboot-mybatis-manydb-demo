package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.secondary.dao"}, sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondaryDataSourceConfig {

    @Bean(name = "secondaryDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    @Primary
    public DataSource secondaryDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private Environment env;

    @Bean(name = "secondarySqlSessionFactory")
    @Primary
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

        factoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        factoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResource(env.getProperty("mybatis.config-location")));
        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(env.getProperty("mybatis.mapper-locations")));
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();

    }

    @Bean(name = "secondaryTransactionManger")
    @Primary
    public DataSourceTransactionManager secondaryTransactionManger(@Qualifier("secondaryDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
