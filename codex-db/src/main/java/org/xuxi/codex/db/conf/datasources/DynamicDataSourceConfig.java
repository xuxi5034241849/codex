package org.xuxi.codex.db.conf.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {



    @Bean
    @ConfigurationProperties("spring.datasource.druid.default")
    public DataSource defaultDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource defaultDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.DEFAULT, defaultDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource(defaultDataSource, targetDataSources);
        return dynamicDataSource;
    }
}
