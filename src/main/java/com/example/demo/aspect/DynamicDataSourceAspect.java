package com.example.demo.aspect;

import com.example.demo.annotation.RouteDataSource;
import com.example.demo.config.DatasourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.example.demo.annotation.RouteDataSource)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void beforeSwitchDataSource(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String dataSource = DatasourceContextHolder.DEFAULT_DATASOURCE;
        if (method.isAnnotationPresent(RouteDataSource.class)) {
            RouteDataSource routeDataSource = method.getDeclaredAnnotation(RouteDataSource.class);
            dataSource = routeDataSource.value();
        }
        DatasourceContextHolder.setDB(dataSource);
        log.info("设置数据源为:{}", dataSource);
    }

    @After("pointcut()")
    public void afterSwitchDataSource() {
        DatasourceContextHolder.clearDB();
        log.info("清空数据源上下文持有者");
    }

}

