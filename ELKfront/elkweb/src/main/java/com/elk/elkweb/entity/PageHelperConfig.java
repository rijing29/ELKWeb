package com.elk.elkweb.entity;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration//将该类加到spring容器里
public class PageHelperConfig {
//    分页实体类
    @Bean//加上该注解spring容器自动配置
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect","oracle");
//        properties.setProperty("autoRuntimeDialect","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
